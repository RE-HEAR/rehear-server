package com.seoridam.rehearserver.fixture;

import java.util.Arrays;
import java.util.List;

import com.seoridam.rehearserver.dto.ArticleForm;

public class ArticleFormFixture {

	public static class ArticleForm1 {

		public static List<Long> ids = Arrays.asList(1L, 3L);
		public static final ArticleForm article = ArticleForm.builder()
			.view(0)
			.introText("test")
			.bodyText("test")
			.title("test title")
			.photoUrl("test url")
			.SubCategoryIdList(ids)
			.build();
	}
}
