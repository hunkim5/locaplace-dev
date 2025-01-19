package com.locaplace.api.host.product.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locaplace.api.host.product.dto.ProductDto;
import com.locaplace.api.host.product.dto.ProductSearchDto;
import com.locaplace.api.host.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Tag(name="상품",description = "상품")
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api/v1/host/product")
public class ProductController {

	private final ProductService productService;

	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "상품 조회")
	@GetMapping("")
    public ResponseEntity<List<ProductDto>> selectProductList(@ParameterObject ProductSearchDto dto) {
		List<ProductDto> list =productService.selectProductList(dto);
        return ResponseEntity.ok(list);
    }
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "상품 상세")
	@GetMapping("/{productNid}")
	public ResponseEntity<ProductDto> selectProduct(@PathVariable("productNid") int productNid) {
		ProductDto data =productService.selectProduct(productNid);
		return ResponseEntity.ok(data);
	}
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "상품 등록")
	@PostMapping("")
    public ResponseEntity<Integer> insertProduct(@RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.insertProduct(dto));
    }
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "상품 수정")
	@PutMapping("")
    public ResponseEntity<Integer> updateProduct(@RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.updateProduct(dto));
    }
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "상품 삭제")
	@DeleteMapping("/{productNid}")
	public ResponseEntity<Integer> deleteProduct(@PathVariable("productNid") int productNid) {
		return ResponseEntity.ok(productService.deleteProduct(productNid));
	}

}
