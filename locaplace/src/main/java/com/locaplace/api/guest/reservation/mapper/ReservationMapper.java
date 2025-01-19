package com.locaplace.api.guest.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.locaplace.api.guest.reservation.dto.ReservaionViewDto;
import com.locaplace.api.guest.reservation.dto.ReservationAlarmDto;
import com.locaplace.api.guest.reservation.dto.ReservationDto;
import com.locaplace.api.guest.reservation.dto.ReservationRegDto;
import com.locaplace.api.guest.reservation.dto.UserCardDto;

@Mapper
public interface ReservationMapper {
	List<ReservationDto> selectReservationList(ReservationDto dto);
	List<ReservationAlarmDto> selectReservationAlarmList(int userNid);
	List<UserCardDto> selectUserCardList(int userCardNid);
	UserCardDto selectUserCard(int userNid);
	ReservationDto selectReservation(int id);
	ReservaionViewDto selectReservaionView(int productNid);
	int insertReservation(ReservationRegDto dto);
	int insertUserCard(UserCardDto dto);
	int updateReservation(ReservationRegDto dto);
	int updateReservationCancel(ReservationDto dto);
	int updateUserCard(UserCardDto dto);
	int updatePaybackAccountCertify(UserCardDto dto);
	int userProductAuthCheck(@Param("productNid") int productNid, @Param("kycLevel") int kycLevel);
	int deleteReservation(int id);
	int deleteUserCard(int userCardNid);
}
