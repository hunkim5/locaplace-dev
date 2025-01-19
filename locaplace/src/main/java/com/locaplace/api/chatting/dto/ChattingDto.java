package com.locaplace.api.chatting.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ChattingDto {
	@Schema(description="채팅일련번호")
	private int chattingNid;
	@Schema(description="채팅룸일련번호")
	private int chattingRoomNid;
	@Schema(description="사용자일련번호")
	private int userNid;
	@Schema(description="메세지")
	private String message;
	@Schema(description="채팅일시")
	private LocalDateTime chattingDtm;
}
