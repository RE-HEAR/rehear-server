package com.seoridam.rehearserver.fixture;

import java.time.LocalDate;
import com.seoridam.rehearserver.domain.Article;

public class ArticleFixture {
		public static final Article article1 = Article.builder()
			.id(1L)
			.title("titleEx")
			.bodyText("bodyTextEx")
			.createDate(LocalDate.now())
			.photoUrl("photoUrlEX")
			.subTitle("subEx")
			.introText("introEx")
			.build();
}
