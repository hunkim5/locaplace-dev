package com.locaplace.api.chatting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.locaplace.api.chatting.dto.ChattingDto;
import com.locaplace.api.chatting.dto.ChattingRoomDto;
import com.locaplace.api.chatting.dto.ChattingSearchDto;

@Mapper
public interface ChattingMapper {
	List<ChattingRoomDto> selectChattingRoomList(ChattingSearchDto dto);
	List<ChattingDto> selectChattingList(int chattingRoomNid);

	ChattingRoomDto selectChattingRoom(int chattingRoomNid);

	int insertChattingRoom(ChattingRoomDto dto);
	int insertChatting(ChattingDto dto);

	int deleteChattingRoom(int id);
	int deleteChatting(int id);
}
