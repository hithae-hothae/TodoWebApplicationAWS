package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;

@RestController
@RequestMapping("test")
public class TestController {
	
	@GetMapping
	public String testController() {
		return "Hello";
	}
	
	@GetMapping("/testGetMapping")
	public String testControllerWithPath() {
		return "Hello testGetMapping";
	}
	
	@GetMapping("/{id}")
	public String testControllerWithPathVar(@PathVariable(required = false) int id) {
		return "Hello ID = " + id;
	}
	
	@GetMapping("/testRequestParam")
	public String testControllerRequsetParam(@RequestParam(required = false) int id) {
		return "Hello ID ! = " + id;
	}
	
	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return "Hello ID = " + testRequestBodyDTO.getId() + " Message = " + testRequestBodyDTO.getMessage();
	}
	
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody() {
		List<String> list = new ArrayList<>();
		list.add("hello ResponseDTO");
		return ResponseDTO.<String>builder().data(list).build();
	}
	
	//ResponseDTO 와의 차이점은 HTTP status 조작 가능
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity() {
		List<String> list = new ArrayList<>();
		list.add("Hello World! I'm ResponseEntity. And you got 400!");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		// http status를 400로 설정.
//		return ResponseEntity.badRequest().body(response);
		// http status 200 OK 설정
		return ResponseEntity.ok().body(response);
		}
	
}
