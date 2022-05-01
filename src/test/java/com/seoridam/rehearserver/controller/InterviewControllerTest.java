package com.seoridam.rehearserver.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.seoridam.rehearserver.dto.InterviewForm;
import com.seoridam.rehearserver.fixture.InterviewFormFixture;
import com.seoridam.rehearserver.service.InterviewService;

@ExtendWith(MockitoExtension.class)
class InterviewControllerTest extends ControllerTest {

	@Autowired
	private InterviewService interviewService;

	@DisplayName("인터뷰 등록")
	@Test
	public void registerInterview() throws Exception {
	    //given
		InterviewForm interviewForm = InterviewFormFixture.InterviewForm1.interview;

		//when
		final ResultActions actions = mvc.perform(post("/admin/interview")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(interviewForm)))
			.andDo(print());

	    //then
		actions
			.andExpect(status().isCreated())
			.andDo(print());
	}

	@DisplayName("인터뷰 내용 조회")
	@Test
	void getInterview() throws Exception {
		//given
		InterviewForm interviewForm = InterviewFormFixture.InterviewForm1.interview;
		Long id = interviewService.registerInterview(interviewForm);

		ResultActions actions = mvc.perform(get("/interview/{id}", id)
			.contentType(MediaType.APPLICATION_JSON)).andDo(print());

		actions.andExpect(status().isOk())
			.andExpect(jsonPath("data.video_url").value(interviewForm.getVideoUrl()))
			.andExpect(jsonPath("data.title").value(interviewForm.getTitle()))
			.andExpect(jsonPath("data.intro_text").value(interviewForm.getIntroText()));
	}

	@DisplayName("인터뷰 목록 리스트 조회 - DB에 interview row가 5가 이상 있다고 가정")
	@Test
	void getInterviewList() throws Exception {
		ResultActions actions = mvc.perform(get("/interview/list/?page=0&size=5")
			.contentType(MediaType.APPLICATION_JSON)).andDo(print());

		actions
			.andExpect(jsonPath("data.content.length()").value(5));
	}

	@DisplayName("인터뷰 목록 리스트 조회 - by subcategoryId")
	@Test
	void getInterviewListBySubcategory() throws Exception {
		ResultActions actions = mvc.perform(get("/interview/list/subcategory/1/?page=0&size=5")
			.contentType(MediaType.APPLICATION_JSON)).andDo(print());
		//db row와 비교 - 일치함
	}
}