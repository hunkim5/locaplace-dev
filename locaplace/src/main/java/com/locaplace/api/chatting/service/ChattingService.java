package com.locaplace.api.chatting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.locaplace.api.chatting.dto.ChattingDto;
import com.locaplace.api.chatting.dto.ChattingRoomDto;
import com.locaplace.api.chatting.dto.ChattingSearchDto;
import com.locaplace.api.chatting.mapper.ChattingMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChattingService {
	private final ChattingMapper chattingMapper;

	public List<ChattingRoomDto> selectChattingRoomList(ChattingSearchDto dto){
		List<ChattingRoomDto> list =chattingMapper.selectChattingRoomList(dto);
		return list;
	}
	public List<ChattingDto> selectChattingList(int chattingRoomNid){
		List<ChattingDto> list =chattingMapper.selectChattingList(chattingRoomNid);
		return list;
	}

	public ChattingRoomDto selectChattingRoom(int chattingRoomNid){
		ChattingRoomDto data=chattingMapper.selectChattingRoom(chattingRoomNid);
		data.setChattingDtos(chattingMapper.selectChattingList(chattingRoomNid));
		return data;
	}
	public int insertChattingRoom(ChattingRoomDto dto) {
		chattingMapper.insertChattingRoom(dto);
		return dto.getChattingRoomNid();
	};
	public int insertChatting(ChattingDto dto){
		return chattingMapper.insertChatting(dto);
	};

	public int deleteChattingRoom(int id){
		chattingMapper.deleteChattingRoom(id);
		return chattingMapper.deleteChatting(id);
	};
}
