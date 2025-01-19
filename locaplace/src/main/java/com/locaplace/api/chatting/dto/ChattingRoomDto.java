package com.locaplace.api.chatting.dto;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ChattingRoomDto {
	@Schema(description="채팅룸일련번호")
	private int chattingRoomNid;
	@Schema(description="채팅개설자")
	private int userNid;
	@Schema(description="채팅참가자(다중참가자 채팅이 없어서 일단 1:1채팅 타입으로 설계)")
	private int joinUserNid;
	@Schema(description="출처일련번호")
	private int sourceNid;
	@Schema(description="룸타입(1:1문의:ONE,예약상품문의:REV,상품문의:PRD)")
	private String roomType;
	@Schema(description="방제목")
	private String roomTitle;
	@Schema(description="채팅방 개설일")
	private LocalDateTime createDtm;
	@Schema(description="예약 상태")
	private String contractStatus;

	@Schema(description="채팅 리스트")
	private List<ChattingDto> chattingDtos;


}
