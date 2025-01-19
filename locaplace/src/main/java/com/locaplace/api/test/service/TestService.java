package com.locaplace.api.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.locaplace.api.test.dto.SubwayStationDto;
import com.locaplace.api.test.dto.TestDto;
import com.locaplace.api.test.mapper.TestMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {
	private final TestMapper testMapper;

	public List<TestDto> selectTestList(TestDto dto) {
		List<TestDto> list =testMapper.selectTestList(dto);
		return list;
	}
	public TestDto selectTest(int testNid) {
		TestDto dto=testMapper.selectTest(testNid,"123");
//		TestDto dto=testMapper.selectTest(testNid);
		return dto;
	}
	public Integer insertTest(TestDto dto) {
		testMapper.insertTest(dto);
		log.debug(">>>>testDto id:{}",dto.getId());
		return 1;
	}
	public Integer updateTest(TestDto dto) {
		return testMapper.updateTest(dto);
	}
	public Integer deleteTest(int id) {
		return testMapper.deleteTest(id);
	}

	public Integer insertSubwayStation(List<SubwayStationDto> list) {
		for(SubwayStationDto dto : list) {
			log.debug(">>getStationNm:{}",dto.getStationNm());
			log.debug(">>getStationNm:{}",dto.getStationCd());
			log.debug(">>getStationNm:{}",dto.getLineNum());
			testMapper.insertSubwayStation(dto);
		}
		return 0;
	}
}
