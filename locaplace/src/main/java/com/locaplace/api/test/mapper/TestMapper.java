package com.locaplace.api.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.locaplace.api.test.dto.SubwayStationDto;
import com.locaplace.api.test.dto.TestDto;
import com.locaplace.api.test.dto.TestSelectDto;

@Mapper
public interface TestMapper {
	List<TestSelectDto> selectTestList2();
	List<TestDto> selectTestList(TestDto dto);
	TestDto selectTest(@Param("id") int id,@Param("str") String str);
//	TestDto selectTest(int id);
	int insertTest(TestDto testDto);
	int insertSubwayStation(SubwayStationDto dto);
	int updateTest(TestDto testDto);
	int deleteTest(int id);
}
