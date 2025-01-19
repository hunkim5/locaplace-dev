package com.locaplace.api.guest.reservation.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReservaionViewDto {
	@Schema(description="상품 일련번호")
	private int productNid;
	@Schema(description="상품명")
	private String productName;
	@Schema(description="이용금액")
	private int useAmount;
	@Schema(description="보증금")
	private int deposit;
	@Schema(description="Guest수수료율")
	private BigDecimal guestFee;
	@Schema(description="수수료")
	private int fee;
	@Schema(description="결재금액")
	private int paymentAmt;
}
