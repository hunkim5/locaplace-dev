package com.locaplace.api.host.product.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnusedDateDto {
	@Schema(description="사용불가 날짜일련번호")
	private int unusedDateNid;
	@Schema(description="상품일련번호")
	private int productNid;
	@Schema(description="사용불가 시작날짜")
	private LocalDate unusedStartDt;
	@Schema(description="사용불가 종료날짜")
	private LocalDate unusedEndDt;
	@Schema(description="생성일")
	private LocalDateTime createDtm;
}
