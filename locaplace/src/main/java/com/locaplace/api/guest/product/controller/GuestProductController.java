package com.locaplace.api.guest.product.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@Tag(name="게스트 상품",description = "상품")
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api/v1/guest/product")
public class GuestProductController {

	private final ProductService productService;
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "상품 조회")
	@GetMapping("")
    public ResponseEntity<List<ProductDto>> selectProductList(@ParameterObject ProductSearchDto dto) {
		List<ProductDto> list =productService.selectProductList(dto);
        return ResponseEntity.ok(list);
    }

}
