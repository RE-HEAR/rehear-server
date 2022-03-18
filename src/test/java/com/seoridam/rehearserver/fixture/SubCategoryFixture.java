package com.seoridam.rehearserver.fixture;

import com.seoridam.rehearserver.domain.SubCategory;

public class SubCategoryFixture {
	public static class SubCategory1{
		public static final SubCategory SUBCATEGORY = SubCategory.builder()
			.id(1L).name("science").build();
	}

	public static class SubCategory2{
		public static final SubCategory SUBCATEGORY = SubCategory.builder()
			.id(2L).name("computer").build();
	}

	public static class SubCategory3{
		public static final SubCategory SUBCATEGORY = SubCategory.builder()
			.id(3L).name("history").build();
	}
}
