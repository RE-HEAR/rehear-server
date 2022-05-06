package com.seoridam.rehearserver.service;

import static com.seoridam.rehearserver.fixture.ArticleFormFixture.ArticleForm1.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.seoridam.rehearserver.domain.Article;
import com.seoridam.rehearserver.repository.ArticleRepository;
import com.seoridam.rehearserver.repository.SubCategoryRepository;
import com.seoridam.rehearserver.repository.TagRepository;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

	@InjectMocks
	private ArticleService articleService;

	@Mock
	private ArticleRepository articleRepository;

	@Mock
	private TagRepository tagRepository;

	@Mock
	private SubCategoryRepository subCategoryRepository;

	@DisplayName("게시글 하나 등록")
	@Test
	public void registerArticle() throws Exception {
		//given
		when(articleRepository.save(any(Article.class))).thenReturn(Article.builder().id(0L).build());
		//when
		Long id = articleService.registerArticle(article);
		//then
		then(articleRepository).should(times(1)).save(any());
		then(subCategoryRepository).should(times(2)).findById(any());
		Assertions.assertEquals(id,0);
	}

	@DisplayName("게시글 하나 조회")
	@Test
	public void getArticle() throws Exception {
		//when
		articleService.getArticle(0L);
		//then
		then(articleRepository).should(times(1)).findArticleProjectionById(any());
	}

	@DisplayName("게시글 리스트 조회")
	@Test
	public void getArticleList() throws Exception {
		//when
		articleService.getArticleList(any());
		//then
		then(articleRepository).should(times(1)).findArticleProjectionsBy(any());
	}

}
