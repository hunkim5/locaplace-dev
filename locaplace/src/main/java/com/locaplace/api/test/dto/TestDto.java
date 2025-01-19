package com.locaplace.api.test.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TestDto {
	@Schema(description = "테스트 id")
	private int id;
	@Schema(description = "테스트 str",defaultValue = "문자")
	private String str;
	@Schema(description = "테스트 del",defaultValue = "N",required = true)
	private String delYn;
	@Schema(description = "테스트 order by",defaultValue = "1")
	private String createDtmYn;
	@Schema(description = "테스트 int")
	private int num;
	@Schema(description = "테스트 소수점",defaultValue = "5.92")
	private BigDecimal pointNum;
	private List<String> strSearch;
	private LocalDateTime createDtm;
	private LocalDate createDt;

}
