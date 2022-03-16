package com.seoridam.rehearserver.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seoridam.rehearserver.dto.InterviewForm;
import com.seoridam.rehearserver.dto.InterviewListSource;
import com.seoridam.rehearserver.dto.InterviewResponseDto;
import com.seoridam.rehearserver.global.common.StatusEnum;
import com.seoridam.rehearserver.global.common.SuccessResponse;
import com.seoridam.rehearserver.service.InterviewService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InterviewController {

	public final InterviewService interviewService;

	@GetMapping("/interview/{id}")
	public SuccessResponse getInterview(@PathVariable Long id){

		InterviewResponseDto interview = interviewService.getInterview(id);

		return SuccessResponse.builder()
			.status(StatusEnum.OK)
			.data(interview)
			.message("인터뷰 조회 성공")
			.build();
	}

	@GetMapping("/interview/list")
	@ApiOperation(value = "내용을 제외한 인터뷰 리스트 목록 반환", notes = "ex: http://localhost:8080/interview/list/?page=3&size=4")
	public SuccessResponse getInterviewList(@RequestParam("page") Integer page, @RequestParam("size") Integer size){

		PageRequest pageRequest = PageRequest.of(page,size);
		Page<InterviewListSource> interviewList = interviewService.getInterviewList(pageRequest);

		return SuccessResponse.builder()
			.status(StatusEnum.OK)
			.data(interviewList)
			.message("인터뷰 조회 성공")
			.build();
	}

	@PostMapping("/admin/interview")
	@ResponseStatus(HttpStatus.CREATED)
	public void registerInterview(@Valid @RequestBody InterviewForm interviewForm){
		interviewService.registerInterview(interviewForm);
	}

}
