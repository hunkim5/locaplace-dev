package com.locaplace.api.host.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.locaplace.api.common.dto.FileMngDto;
import com.locaplace.api.host.product.dto.ProductDto;
import com.locaplace.api.host.product.dto.ProductOptionInfoDto;
import com.locaplace.api.host.product.dto.ProductSearchDto;
import com.locaplace.api.host.product.dto.ReviewDto;
import com.locaplace.api.host.product.dto.UnusedDateDto;

@Mapper
public interface ProductMapper {
	Integer insertProduct(ProductDto productDto);
	Integer insertProductOptionInfo(ProductOptionInfoDto productOptionInfoDto);
	Integer insertUnusedDate(UnusedDateDto unusedDateDto);
	Integer insertFileMng(FileMngDto dto);

	Integer updateProduct(ProductDto productDto);

	Integer deleteProduct(int productNid);
	Integer deleteProductOptionInfo(int productNid);
	Integer deleteUnusedDate(int productNid);
	Integer deleteFileMng(int productNid);

	ProductDto selectProduct(int productNid);
	List<ProductDto> selectProductList(ProductSearchDto productDto);
	List<ProductDto> selectGuestProductList(ProductSearchDto productDto);
	List<ProductOptionInfoDto> selectProductOptionInfoList(int productNid);
	List<UnusedDateDto> selectUnusedDateList(int productNid);
	List<ReviewDto> selectProductReviewList(int productNid);
	List<FileMngDto> selectFileMngList(int productNid);
}
