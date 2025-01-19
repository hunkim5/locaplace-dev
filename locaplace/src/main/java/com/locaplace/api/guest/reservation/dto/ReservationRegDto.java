package com.locaplace.api.guest.reservation.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReservationRegDto {
	@Schema(description="예약일련번호")
	private int reservationNid;
	@Schema(description="상품일련번호",required = true)
	private int productNid;
	@Schema(description="호스트일련번호",required = true)
	private int hostNid;
	@Schema(description="게스트일련번호",required = true)
	private int userNid;
	@Schema(description="계약 시작일(입실시작일과동일)",required = true)
	private LocalDate contractStartDtm;
	@Schema(description="계약종료일(퇴실일과동일)",required = true)
	private LocalDate contractEndDtm;
	@Schema(description="게스트명수",required = true)
	private int guestCount;
	@Schema(description="공통코드 contract_status 참조(0:승인대기,1:예약취소,2:승인완료,3:승인취소,4:결제완료,5:결제실패,6:이용중,7:계약연장,8:계약종료)")
	private int contractStatus;
}
