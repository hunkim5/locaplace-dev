package com.locaplace.api.guest.reservation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserCardDto {
	@Schema(description="사용자카드일련번호")
	private int userCardNid;
	@Schema(description="사용자일련번호")
	private int userNid;
	@Schema(description="카드사(코드화필요)")
	private String cardCode;
	@Schema(description="개일:PERSONAL,법인:CORPORATE")
	private String cardGubun;
	@Schema(description="생년월일")
	private LocalDate birthday;
	@Schema(description="사업자번호")
	private String businessNumber;
	@Schema(description="카드번호")
	private String cardNumber;
	@Schema(description="유효기간")
	private String validit;
	@Schema(description="카드비밀번호")
	private String cardPassword;
	@Schema(description="메이카드여부")
	private String mainYn;
	@Schema(description="생성일")
	private LocalDateTime createDtm;
	@Schema(description="수정일")
	private LocalDateTime updateDtm;
}
