package com.locaplace.api.guest.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.locaplace.api.guest.reservation.dto.ReservaionViewDto;
import com.locaplace.api.guest.reservation.dto.ReservationAlarmDto;
import com.locaplace.api.guest.reservation.dto.ReservationDto;
import com.locaplace.api.guest.reservation.dto.ReservationRegDto;

@Mapper
public interface ReservationMapper {
	List<ReservationDto> selectReservationList(ReservationDto dto);
	List<ReservationAlarmDto> selectReservationAlarmList(int userNid);
	ReservationDto selectReservation(int id);
	ReservaionViewDto selectReservaionView(int productNid);
	int insertReservation(ReservationRegDto testDto);
	int updateReservation(ReservationRegDto testDto);
	int deleteReservation(int id);
	int userProductAuthCheck(@Param("productNid") int productNid, @Param("kycLevel") int kycLevel);
}
