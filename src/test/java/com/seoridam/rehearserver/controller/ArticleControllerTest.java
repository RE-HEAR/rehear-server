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

import com.seoridam.rehearserver.dto.ArticleForm;
import com.seoridam.rehearserver.fixture.ArticleFormFixture;
import com.seoridam.rehearserver.service.ArticleService;

@ExtendWith(MockitoExtension.class)
class ArticleControllerTest extends ControllerTest {

	@Autowired
	private ArticleService articleService;

	@DisplayName("인터뷰 등록")
	@Test
	public void registerArticle() throws Exception {
	    //given
		ArticleForm articleForm = ArticleFormFixture.ArticleForm1.article;

		//when
		final ResultActions actions = mvc.perform(post("/admin/article")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(articleForm)))
			.andDo(print());

	    //then
		actions
			.andExpect(status().isCreated())
			.andDo(print());
	}

	@DisplayName("인터뷰 내용 조회")
	@Test
	void getArticle() throws Exception {
		//given
		ArticleForm articleForm = ArticleFormFixture.ArticleForm1.article;
		Long id = articleService.registerArticle(articleForm);

		ResultActions actions = mvc.perform(get("/article/{id}", id)
			.contentType(MediaType.APPLICATION_JSON)).andDo(print());

		actions.andExpect(status().isOk())
			.andExpect(jsonPath("data.title").value(articleForm.getTitle()))
			.andExpect(jsonPath("data.intro_text").value(articleForm.getIntroText()));
	}

	@DisplayName("인터뷰 목록 리스트 조회 - DB에 article row가 5가 이상 있다고 가정")
	@Test
	void getArticleList() throws Exception {
		ResultActions actions = mvc.perform(get("/article/list/?page=0&size=5")
			.contentType(MediaType.APPLICATION_JSON)).andDo(print());

		actions
			.andExpect(jsonPath("data.content.length()").value(5));
	}

	@DisplayName("인터뷰 목록 리스트 조회 - by subcategoryId")
	@Test
	void getArticleListBySubcategory() throws Exception {
		ResultActions actions = mvc.perform(get("/article/list/subcategory/1/?page=0&size=5")
			.contentType(MediaType.APPLICATION_JSON)).andDo(print());
		//db row와 비교 - 일치함
	}
}