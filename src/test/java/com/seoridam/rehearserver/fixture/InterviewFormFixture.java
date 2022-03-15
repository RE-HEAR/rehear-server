package com.seoridam.rehearserver.fixture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.seoridam.rehearserver.domain.InterviewForm;

public class InterviewFormFixture {

	public static class InterviewForm1 {

		public static List<Long> ids = Arrays.asList(1L, 3L);
		public static final InterviewForm interview = InterviewForm.builder()
			.view(0)
			.introText("test")
			.bodyText("test")
			.title("test title")
			.photoUrl("test url")
			.videoUrl("test vid")
			.SubCategoryIdList(ids)
			.build();
	}
}
