package com.locaplace.api.test;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locaplace.api.test.dto.SubwayStationDto;
import com.locaplace.api.test.dto.TestDto;
import com.locaplace.api.test.dto.TestSelectDto;
import com.locaplace.api.test.mapper.TestMapper;
import com.locaplace.api.test.service.TestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Tag(name="testController",description = "Test")
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/test")
public class TestController {

	private final TestService testService;
	private final TestMapper map;

	@SecurityRequirement(name = "bearer-token")
	@Operation(summary = "select Test",description = "test desc")
	@GetMapping("/test24")
    public ResponseEntity<List<TestSelectDto>> selectTestList2() {
		List<TestSelectDto> list =map.selectTestList2();
		log.debug(">>>>list:{}",list.toString());
        return ResponseEntity.ok(list);
    }

	@Operation(summary = "select Test",description = "test desc")
	@GetMapping("")
    public ResponseEntity<List<TestDto>> selectTestList(@ParameterObject TestDto dto) {
		log.debug(">>dto:{}",dto);
		List<TestDto> list =testService.selectTestList(dto);
		log.debug(">>>>list:{}",list.get(0).getStr());
        return ResponseEntity.ok(list);
    }
	@Operation(summary = "select Test 상세",description = "test desc")
	@GetMapping("/{id}/{str}")
	public ResponseEntity<TestDto> selectTest(@PathVariable("id") int id,@PathVariable("str") String str) {
		log.debug(">>>>>>>str:{}",str);
		TestDto list =testService.selectTest(id);
		return ResponseEntity.ok(list);
	}
	@Operation(summary = "select Test 상세Test",description = "test desc")
	@GetMapping("/test/{num}")
	public ResponseEntity<TestDto> selectTest1(@PathVariable("num") int id) {
		TestDto list =testService.selectTest(id);
		return ResponseEntity.ok(list);
	}
	@Operation(summary = "insert test",description = "test desc")
	@PostMapping("")
    public ResponseEntity<Integer> insertTest(@RequestBody TestDto dto) {
		log.debug(">>>dto:{}",dto.getStr());
        return ResponseEntity.ok(testService.insertTest(dto));
    }
	@Operation(summary = "update test",description = "test desc")
	@PutMapping("")
    public ResponseEntity<Integer> updateTest(@RequestBody TestDto dto) {
		log.debug(">>>dto:{}",dto.getStr());
        return ResponseEntity.ok(testService.updateTest(dto));
    }
	@Operation(summary = "delete test")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> deleteTest(@PathVariable("id") int id) {
		log.debug(">>>id:{}",id);
		return ResponseEntity.ok(testService.deleteTest(id));
	}

	@PostMapping("/insertSubway")
    public ResponseEntity<Integer> insertSubwayStation(@RequestBody List<SubwayStationDto> list) {
		log.debug(">>>list size:{}",list.size());
        return ResponseEntity.ok(testService.insertSubwayStation(list));
    }
}
