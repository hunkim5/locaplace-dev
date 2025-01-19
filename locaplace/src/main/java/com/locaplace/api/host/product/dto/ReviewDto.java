package com.locaplace.api.host.product.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReviewDto {
	@Schema(description="리뷰일련번호")
	private int reviewNid;
	@Schema(description="사용자일련번호")
	private int userNid;
	@Schema(description="상품일련번호")
	private int productNid;
	@Schema(description="예약일련번호")
	private int reservationNid;
	@Schema(description="리뷰 내용")
	private String reviewContent;
	@Schema(description="만족도 점수")
	private BigDecimal reviewScore;
	@Schema(description="생성일")
	private LocalDateTime createDtm;
}
