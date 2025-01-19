package com.locaplace.api.guest.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.locaplace.api.guest.reservation.dto.ReservaionViewDto;
import com.locaplace.api.guest.reservation.dto.ReservationAlarmDto;
import com.locaplace.api.guest.reservation.dto.ReservationDto;
import com.locaplace.api.guest.reservation.dto.ReservationRegDto;
import com.locaplace.api.guest.reservation.mapper.ReservationMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
	private final ReservationMapper reservationMapper;

	public List<ReservationDto> selectReservationList(ReservationDto dto) {
		List<ReservationDto> list =reservationMapper.selectReservationList(dto);
		return list;
	}
	public List<ReservationAlarmDto> selectReservationAlarmList(int userNid) {
		List<ReservationAlarmDto> list =reservationMapper.selectReservationAlarmList(userNid);
		return list;
	}
	public ReservationDto selectReservation(int reservationNid) {
		ReservationDto dto=reservationMapper.selectReservation(reservationNid);
		return dto;
	}
	public ReservaionViewDto selectReservaionView(int productNid) {
		ReservaionViewDto dto=reservationMapper.selectReservaionView(productNid);
		return dto;
	}
	public Integer insertReservation(ReservationRegDto dto) {
		return reservationMapper.insertReservation(dto);
	}
	public Integer updateReservation(ReservationRegDto dto) {
		return reservationMapper.updateReservation(dto);
	}
	public Integer deleteReservation(int id) {
		return reservationMapper.deleteReservation(id);
	}
	public Integer userProductAuthCheck(int productNid,int kycLevel) {
		return reservationMapper.userProductAuthCheck(productNid, kycLevel);
	}

}
