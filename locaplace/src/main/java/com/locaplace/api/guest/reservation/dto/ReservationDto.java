package com.locaplace.api.guest.reservation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDto {
	@Schema(description="예약일련번호")
	private int reservationNid;
	@Schema(description="상품일련번호")
	private int productNid;
	@Schema(description="호스트일련번호")
	private int hostNid;
	@Schema(description="게스트일련번호")
	private int userNid;
	@Schema(description="상품명")
	private String productName;
	@Schema(description="계약 시작일(입실시작일과동일)")
	private LocalDate contractStartDtm;
	@Schema(description="계약종료일(퇴실일과동일)")
	private LocalDate contractEndDtm;
	@Schema(description="게스트명수")
	private int guestCount;
	@Schema(description="일반:NORMAL,정기:REGULAR")
	private String contractType;
	@Schema(description="공통코드 contract_status 참조(0:승인대기,1:예약취소,2:승인완료,3:승인취소,4:결제완료,5:결제실패,6:이용중,7:계약연장,8:계약종료)")
	private int contractStatus;
	@Schema(description="예약요청날짜(예약일과동일)")
	private LocalDateTime revReqDt;
	@Schema(description="예약요청일시(예약일과동일)")
	private LocalDateTime revReqDtm;
	@Schema(description="예약취소일시")
	private LocalDateTime revCancelDtm;
	@Schema(description="승인완료일시")
	private LocalDateTime approved;
	@Schema(description="승인취소일시")
	private LocalDateTime approvalCancelDtm;
	@Schema(description="결제완료일시")
	private LocalDateTime paymentDtm;
	@Schema(description="결제실패일시")
	private LocalDateTime paymentFailDtm;
	@Schema(description="결제금액")
	private int paymentAmt;
	@Schema(description="환불금액")
	private int paybackAmt;
	@Schema(description="카드(CARD),간편(SIMPLE),무통장(BANK)")
	private String paymentType;
	@Schema(description="은행명")
	private String bankName;
	@Schema(description="계좌번호")
	private String accountNo;
	@Schema(description="계좌소유자명")
	private String accountOwner;
	@Schema(description="계약연장여부")
	private String extContractYn;
	@Schema(description="보증금반환여부")
	private String depositReturnYn;
	@Schema(description="생성일")
	private LocalDateTime createDtm;
	@Schema(description="수정일")
	private LocalDateTime updateDtm;
}
