package com.locaplace.api.test.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TestSelectDto {
	@Schema(description = "테스트 id")
	private int id;
	private LocalDate createDt;
}
