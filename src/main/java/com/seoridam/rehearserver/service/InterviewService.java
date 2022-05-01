package com.seoridam.rehearserver.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seoridam.rehearserver.domain.Interview;
import com.seoridam.rehearserver.dto.InterviewForm;
import com.seoridam.rehearserver.domain.SubCategory;
import com.seoridam.rehearserver.domain.Tag;
import com.seoridam.rehearserver.domain.TopLike;
import com.seoridam.rehearserver.dto.InterviewProjection;
import com.seoridam.rehearserver.dto.InterviewResponseDto;
import com.seoridam.rehearserver.repository.InterviewRepository;
import com.seoridam.rehearserver.repository.SubCategoryRepository;
import com.seoridam.rehearserver.repository.TagRepository;
import com.seoridam.rehearserver.repository.TopLikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class InterviewService {
	private final InterviewRepository interviewRepository;
	private final TagRepository tagRepository;
	private final TopLikeRepository topLikeRepository;
	private final SubCategoryRepository subCategoryRepository;

	// 인터뷰 하나 조회
	@Transactional(readOnly = true)
	public InterviewResponseDto getInterview(Long id) {

		Optional<Interview> found = interviewRepository.findById(id);
		Interview interview = found.orElseThrow(RuntimeException::new);

		List<Tag> tagList = interview.getTagList();
		List<String> subcategory_names = tagList.stream().map(tag -> tag.getSubCategory().getName()).collect(Collectors.toList());

		return InterviewResponseDto.builder()
			.createDate(interview.getCreateDate())
			.body_text(interview.getBodyText())
			.intro_text(interview.getIntroText())
			.sub_title(interview.getSubTitle())
			.photo_url(interview.getPhotoUrl())
			.subcategory_names(subcategory_names)
			.view(interview.getView())
			.video_url(interview.getVideoUrl())
			.title(interview.getTitle()).build();
	}

	//인터뷰 리스트 목록 조회
	@Transactional(readOnly = true)
	public Page<InterviewProjection> getInterviewList(PageRequest pageRequest){
		return interviewRepository.findInterviewProjectionsBy(pageRequest);
	}

	// 인터뷰 저장
	public Long registerInterview(InterviewForm form){
		Interview interview = Interview.builder()
			.view(0)
			.videoUrl(form.getVideoUrl())
			.photoUrl(form.getPhotoUrl())
			.title(form.getTitle())
			.subTitle(form.getSubTitle())
			.introText(form.getIntroText())
			.bodyText(form.getBodyText()).build();
		Interview savedInterview = interviewRepository.save(interview);

		//toplike 초기화 및 저장
		TopLike topLike = TopLike.builder().total(0).likeWeek(0).likeToday(0).likePastday(0).build();
		topLike.setInterview(savedInterview);
		topLikeRepository.save(topLike);

		//관련 tag 저장
		List<Long> idList = form.getSubCategoryIdList();
		for (Long subId : idList) {
			Optional<SubCategory> sub = subCategoryRepository.findById(subId);
			sub.ifPresent(subCategory -> {
				Tag setTag = Tag.builder().interview(savedInterview).subCategory(subCategory).build();
				tagRepository.save(setTag);
			});
		}
		return savedInterview.getId();
	}
}
