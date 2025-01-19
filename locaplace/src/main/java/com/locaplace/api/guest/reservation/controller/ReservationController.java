package com.locaplace.api.guest.reservation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locaplace.api.guest.reservation.dto.ReservaionViewDto;
import com.locaplace.api.guest.reservation.dto.ReservationAlarmDto;
import com.locaplace.api.guest.reservation.dto.ReservationDto;
import com.locaplace.api.guest.reservation.dto.ReservationRegDto;
import com.locaplace.api.guest.reservation.dto.UserCardDto;
import com.locaplace.api.guest.reservation.service.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Tag(name="예약")
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api/v1/reservation")
public class ReservationController {

	private final ReservationService reservationService;

	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "예약 리스트")
	@GetMapping("")
    public ResponseEntity<List<ReservationDto>> selectReservationList(@RequestBody ReservationDto dto) {
		List<ReservationDto> list =reservationService.selectReservationList(dto);
        return ResponseEntity.ok(list);
    }
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "예약 상세")
	@GetMapping("/{reservationNid}")
	public ResponseEntity<ReservationDto> selectReservation(@PathVariable("reservationNid") int reservationNid) {
		ReservationDto data =reservationService.selectReservation(reservationNid);
		return ResponseEntity.ok(data);
	}
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "예약하기 화면")
	@GetMapping("/reservaion-view/{productNid}")
	public ResponseEntity<ReservaionViewDto> selectReservaionView(@PathVariable("productNid") int productNid) {
		ReservaionViewDto data =reservationService.selectReservaionView(productNid);
		return ResponseEntity.ok(data);
	}
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "사용자와상품간 인증단계 체크", description = "인증단계가 서로 맞지 않을시 예약불가")
	@GetMapping("/auth-check/{productNid}/{kycLevel}")
	public ResponseEntity<Integer> userProductAuthCheck(@PathVariable("productNid") int productNid,@PathVariable("kycLevel") int kycLevel) {
		return ResponseEntity.ok(reservationService.userProductAuthCheck(productNid, kycLevel));
	}
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "예약 등록")
	@PostMapping("")
    public ResponseEntity<Integer> insertReservation(@RequestBody ReservationRegDto dto) {
        return ResponseEntity.ok(reservationService.insertReservation(dto));
    }
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "예약 수정")
	@PutMapping("")
    public ResponseEntity<Integer> updateReservation(@RequestBody ReservationRegDto dto) {
        return ResponseEntity.ok(reservationService.updateReservation(dto));
    }
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "예약 알림 리스트")
	@GetMapping("/reservation-alarm/{userNid}")
    public ResponseEntity<List<ReservationAlarmDto>> selectReservationAlarmList(@PathVariable("userNid") int userNid) {
		List<ReservationAlarmDto> list =reservationService.selectReservationAlarmList(userNid);
        return ResponseEntity.ok(list);
    }
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "예약 결제하기 화면")
	@GetMapping("/rev-payment-view/{reservationNid}")
	public ResponseEntity<ReservationDto> selectReservationPaymentView(@PathVariable("reservationNid") int reservationNid) {
		ReservationDto data =reservationService.selectReservation(reservationNid);
		return ResponseEntity.ok(data);
	}
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "등록카드 관리")
	@GetMapping("/user-card/{userNid}")
    public ResponseEntity<List<UserCardDto>> selectUserCardList(@PathVariable("userNid") int userNid) {
		List<UserCardDto> list =reservationService.selectUserCardList(userNid);
        return ResponseEntity.ok(list);
    }
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "카드 추가하기")
	@PostMapping("/user-card")
	public ResponseEntity<Integer> insertUserCard(@RequestBody UserCardDto dto) {
		return ResponseEntity.ok(reservationService.insertUserCard(dto));
	}
	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "카드 삭제")
	@DeleteMapping("/user-card/{userCardNid}")
	public ResponseEntity<Integer> deleteUserCard(@PathVariable("userCardNid") int userCardNid) {
		return ResponseEntity.ok(reservationService.deleteUserCard(userCardNid));
	}
}
