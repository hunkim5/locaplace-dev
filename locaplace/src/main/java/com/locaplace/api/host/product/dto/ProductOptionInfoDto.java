package com.locaplace.api.host.product.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOptionInfoDto {
	@Schema(description="상품기본정보일련번호")
	private int productOptionInfoNid;
	@Schema(description="상품일련번호")
	private int productNid;
	@Schema(description="분류코드키")
	private String classificationCode;
	@Schema(description="공통코드 일련번호")
	private int commonCodeNid;
	@Schema(description="생성일시")
	private LocalDateTime createDtm;
}
