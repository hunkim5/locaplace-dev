package com.locaplace.api.guest.reservation.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationGuestRegDto {
	@Schema(description="예약일련번호")
	private int reservationNid;
	@Schema(description="상품일련번호")
	private int productNid;
	@Schema(description="상품명")
	private String productName;
	@Schema(description="호스트일련번호")
	private int hostNid;
	@Schema(description="게스트일련번호")
	private int userNid;
	@Schema(description="계약 시작일(입실시작일과동일)")
	private LocalDate contractStartDtm;
	@Schema(description="계약종료일(퇴실일과동일)")
	private LocalDate contractEndDtm;
	@Schema(description="게스트명수")
	private int guestCount;
	@Schema(description="이용금액")
	private int useAmount;
	@Schema(description="보증금")
	private int deposit;
	@Schema(description="Guest수수료율")
	private BigDecimal guestFee;
	@Schema(description="수수료")
	private int fee;
	@Schema(description="결제금액")
	private int paymentAmt;
}
