package com.example.product_category_api.controller;

import com.example.product_category_api.DTO.ProductDTO;
import com.example.product_category_api.entity.Product;
import com.example.product_category_api.mapper.ProductMapper;
import com.example.product_category_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/getbyUuid/{uuid}")
    public ResponseEntity<ProductDTO> getProductByUuid(@PathVariable UUID uuid) {
        Product product = productService.getProductByUuid(uuid);
        ProductDTO productDTO = productMapper.toProductDTO(product);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Product> products = productService.getProducts(PageRequest.of(page, size));
        Page<ProductDTO> productDTOs = products.map(productMapper::toProductDTO);
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/enabled")
    public ResponseEntity<Page<ProductDTO>> getEnabledProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Product> products = productService.getEnabledProducts(PageRequest.of(page, size));
        Page<ProductDTO> productDTOs = products.map(productMapper::toProductDTO);
        return ResponseEntity.ok(productDTOs);
    }

    @PostMapping("/create/")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        Product createdProduct = productService.createProduct(product);
        ProductDTO createdProductDTO = productMapper.toProductDTO(createdProduct);
        return new ResponseEntity<>(createdProductDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{uuid}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID uuid, @RequestBody ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        Product updatedProduct = productService.updateProduct(uuid, product);
        ProductDTO updatedProductDTO = productMapper.toProductDTO(updatedProduct);
        return ResponseEntity.ok(updatedProductDTO);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID uuid) {
        productService.deleteProduct(uuid);
        return ResponseEntity.noContent().build();
    }
}
