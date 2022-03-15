package com.seoridam.rehearserver.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.seoridam.rehearserver.domain.InterviewForm;
import com.seoridam.rehearserver.domain.SubCategory;
import com.seoridam.rehearserver.fixture.InterviewFormFixture;
import com.seoridam.rehearserver.fixture.SubCategoryFixture;
import com.seoridam.rehearserver.repository.SubCategoryRepository;

@ExtendWith(MockitoExtension.class)
class InterviewControllerTest extends ControllerTest {

	@Mock
	private SubCategoryRepository subCategoryRepository;

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

	@Test
	void getInterview() {
	}

	@Test
	void getInterviewList() {
	}

}