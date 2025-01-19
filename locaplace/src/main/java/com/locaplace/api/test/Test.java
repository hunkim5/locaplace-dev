package com.locaplace.api.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDateTime now1 = LocalDateTime.now();
		LocalDate now = now1.toLocalDate();
		LocalDate end = LocalDate.parse("2025-01-21");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		System.out.println(now.format(formatter));
		System.out.println((int)Math.ceil(10.999));
		BigDecimal f;
	}

}
