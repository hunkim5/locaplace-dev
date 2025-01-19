package com.locaplace.api.guest.reservation.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.locaplace.api.guest.reservation.dto.ReservaionViewDto;
import com.locaplace.api.guest.reservation.dto.ReservationAlarmDto;
import com.locaplace.api.guest.reservation.dto.ReservationDto;
import com.locaplace.api.guest.reservation.dto.ReservationRegDto;
import com.locaplace.api.guest.reservation.dto.UserCardDto;
import com.locaplace.api.guest.reservation.mapper.ReservationMapper;
import com.locaplace.api.host.product.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
	private final ReservationMapper reservationMapper;
	private final ProductMapper productMapper;

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
		dto.setProductDto(productMapper.selectProduct(dto.getProductNid()));
		dto.setUserCardDtos(reservationMapper.selectUserCardList(dto.getUserNid()));
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
	public Integer userProductAuthCheck(int productNid,int kycLevel) {
		return reservationMapper.userProductAuthCheck(productNid, kycLevel);
	}
	public Integer updateReservationCancel(int reservationNid) {
		ReservationDto dto=reservationMapper.selectReservation(reservationNid);
		int paymentAmt = dto.getPaymentAmt(); //결제금액
		LocalDate paymentDtm = dto.getPaymentDtm().toLocalDate(); //결제일
		LocalDate contractStartDtm = dto.getContractStartDtm(); //입실일
		LocalDate now = LocalDate.now();
		int pastDay = now.compareTo(paymentDtm);
		int leftDay = contractStartDtm.compareTo(now);
		double refundRate = 0.9;
		if(pastDay == 0) {
			refundRate = 1;
		}else if(leftDay >= 15 ) {
			refundRate = 0.9;
		}else if(leftDay < 15 && leftDay >= 8) {
			refundRate = 0.7;
		}else if(leftDay < 8 && leftDay >= 1) {
			refundRate = 0.5;
		}else {
			refundRate = 0;
		}
		int paybackAmt =(int)(Math.ceil(paymentAmt * refundRate));
		dto.setPaybackAmt(paybackAmt);

		return reservationMapper.updateReservationCancel(dto);
	}

	public List<UserCardDto> selectUserCardList(int userNid) {
		List<UserCardDto> list =reservationMapper.selectUserCardList(userNid);
		return list;
	}
	public Integer insertUserCard(UserCardDto dto) {
		return reservationMapper.insertUserCard(dto);
	}
	public Integer deleteUserCard(int userCardNid) {
		return reservationMapper.deleteUserCard(userCardNid);
	}
}
