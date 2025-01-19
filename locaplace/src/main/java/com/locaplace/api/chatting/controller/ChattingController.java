package com.locaplace.api.chatting.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locaplace.api.chatting.dto.ChattingDto;
import com.locaplace.api.chatting.dto.ChattingRoomDto;
import com.locaplace.api.chatting.dto.ChattingSearchDto;
import com.locaplace.api.chatting.service.ChattingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Tag(name="채팅")
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api/v1/chatting")
public class ChattingController {

	private final ChattingService chattingService;

	@Operation(summary = "채팅룸 리스트")
	@GetMapping("")
    public ResponseEntity<List<ChattingRoomDto>> selectChattingRoomList(@RequestBody ChattingSearchDto dto) {
		List<ChattingRoomDto> list =chattingService.selectChattingRoomList(dto);
        return ResponseEntity.ok(list);
    }
	@Operation(summary = "채팅룸 상세")
	@GetMapping("/{chattingRoomNid}")
	public ResponseEntity<ChattingRoomDto> selectChattingRoomList(@PathVariable("chattingRoomNid") int chattingRoomNid) {
		ChattingRoomDto data =chattingService.selectChattingRoom(chattingRoomNid);
		return ResponseEntity.ok(data);
	}

	@Operation(summary = "채팅룸 등록(채팅룸 일련번호 리턴)")
	@PostMapping("/room")
    public ResponseEntity<Integer> insertChattingRoom(@RequestBody ChattingRoomDto dto) {
        return ResponseEntity.ok(chattingService.insertChattingRoom(dto));
    }
	@Operation(summary = "채팅 등록")
	@PostMapping("")
	public ResponseEntity<Integer> insertChatting(@RequestBody ChattingDto dto) {
		return ResponseEntity.ok(chattingService.insertChatting(dto));
	}

	@Operation(summary = "채팅룸 삭제")
	@DeleteMapping("/room/{chattingRoomNid}")
	public ResponseEntity<Integer> deleteChattingRoom(@PathVariable("chattingRoomNid") int chattingRoomNid) {
		return ResponseEntity.ok(chattingService.deleteChattingRoom(chattingRoomNid));
	}
}
