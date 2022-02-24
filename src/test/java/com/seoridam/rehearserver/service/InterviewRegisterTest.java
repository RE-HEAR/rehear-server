package com.seoridam.rehearserver.service;

import static com.seoridam.rehearserver.fixture.InterviewFormFixture.InterviewForm1.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.seoridam.rehearserver.domain.SubCategory;
import com.seoridam.rehearserver.fixture.SubCategoryFixture;
import com.seoridam.rehearserver.repository.InterviewRepository;
import com.seoridam.rehearserver.repository.SubCategoryRepository;
import com.seoridam.rehearserver.repository.TagRepository;
import com.seoridam.rehearserver.repository.TopLikeRepository;

@ExtendWith(MockitoExtension.class)
public class InterviewRegisterTest {

	@InjectMocks
	private InterviewService interviewService;

	@Mock
	private InterviewRepository interviewRepository;

	@Mock
	private TopLikeRepository topLikeRepository;

	@Mock
	private TagRepository tagRepository;

	@Mock
	private SubCategoryRepository subCategoryRepository;

	@DisplayName("인터뷰 하나 등록")
	@Test
	public void registerInterview() throws Exception {
		//given
		final Optional<SubCategory> sub1 = Optional.of(SubCategoryFixture.SubCategory1.SUBCATEGORY);
		final Optional<SubCategory> sub2 = Optional.of(SubCategoryFixture.SubCategory2.SUBCATEGORY);
		given(subCategoryRepository.findById(1L)).willReturn(sub1);
		given(subCategoryRepository.findById(3L)).willReturn(sub2);
		//when
		interviewService.registerInterview(interview);
	    //then
		then(interviewRepository).should(times(1)).save(any());
		then(topLikeRepository).should(times(1)).save(any());
		then(tagRepository).should(times(2)).save(any());
	}
}
