package com.locaplace.api.host.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.locaplace.api.common.dto.FileMngDto;
import com.locaplace.api.host.product.dto.ProductDto;
import com.locaplace.api.host.product.dto.ProductOptionInfoDto;
import com.locaplace.api.host.product.dto.ProductSearchDto;
import com.locaplace.api.host.product.dto.UnusedDateDto;
import com.locaplace.api.host.product.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductMapper productMapper;

	public List<ProductDto> selectProductList(ProductSearchDto dto) {
		List<ProductDto> list =productMapper.selectProductList(dto);
		return list;
	}
	public ProductDto selectProduct(int productNid) {
		ProductDto dto=productMapper.selectProduct(productNid);
		dto.setProductOptionInfoDtos(productMapper.selectProductOptionInfoList(productNid));
		dto.setUnusedDateDtos(productMapper.selectUnusedDateList(productNid));
		dto.setReviewDtos(productMapper.selectProductReviewList(productNid));
		dto.setFileMngDtos(productMapper.selectFileMngList(productNid));
		return dto;
	}
	public Integer insertProduct(ProductDto dto) {
		int result=0;
		result = productMapper.insertProduct(dto);
		for(ProductOptionInfoDto optionDto: dto.getProductOptionInfoDtos()) {
			optionDto.setProductNid(dto.getProductNid());
			result += productMapper.insertProductOptionInfo(optionDto);
		}
		for(UnusedDateDto data: dto.getUnusedDateDtos()) {
			data.setProductNid(dto.getProductNid());
			result += productMapper.insertUnusedDate(data);
		}
		for(FileMngDto fileDto: dto.getFileMngDtos()) {
			fileDto.setFileGroupNid(dto.getProductNid());
			result += productMapper.insertFileMng(fileDto);
		}
		return result;
	}
	public Integer updateProduct(ProductDto dto) {
		int result=0;
		result = productMapper.updateProduct(dto);

		result += productMapper.deleteProductOptionInfo(dto.getProductNid());
		for(ProductOptionInfoDto optionDto: dto.getProductOptionInfoDtos()) {
			optionDto.setProductNid(dto.getProductNid());
			result += productMapper.insertProductOptionInfo(optionDto);
		}

		result += productMapper.deleteUnusedDate(dto.getProductNid());
		for(UnusedDateDto unusedDateDto: dto.getUnusedDateDtos()) {
			unusedDateDto.setProductNid(dto.getProductNid());
			result += productMapper.insertUnusedDate(unusedDateDto);
		}

		result += productMapper.deleteFileMng(dto.getProductNid());
		for(FileMngDto fileDto: dto.getFileMngDtos()) {
			fileDto.setFileGroupNid(dto.getProductNid());
			result += productMapper.insertFileMng(fileDto);
		}
		return result;
	}
	public Integer deleteProduct(int productNid) {
		int result=0;
		result = productMapper.deleteProduct(productNid);
		result += productMapper.deleteProductOptionInfo(productNid);
		result += productMapper.deleteUnusedDate(productNid);
		result += productMapper.deleteFileMng(productNid);

		return result;
	}

}
