package com.seoridam.rehearserver.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seoridam.rehearserver.domain.Interview;
import com.seoridam.rehearserver.domain.InterviewForm;
import com.seoridam.rehearserver.domain.SubCategory;
import com.seoridam.rehearserver.domain.Tag;
import com.seoridam.rehearserver.domain.TopLike;
import com.seoridam.rehearserver.dto.InterviewListDto;
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
			.id(interview.getId())
			.createDate(interview.getCreateDate())
			.body_text(interview.getBody_text())
			.intro_text(interview.getIntro_text())
			.sub_title(interview.getSub_title())
			.photo_url(interview.getPhoto_url())
			.subcategory_names(subcategory_names)
			.view(interview.getView())
			.video_url(interview.getVideo_url())
			.title(interview.getTitle()).build();
	}

	//인터뷰 리스트 목록 조회
	@Transactional(readOnly = true)
	public Page<InterviewListDto> getInterviewList(PageRequest pageRequest){
		Page<Interview> interviewList = interviewRepository.findAll(pageRequest);

		List<InterviewListDto> interviewListDtos =
			interviewList.stream().map(interview -> {
				//태그 이름만 모아서 리스트 생성
				List<Tag> tagList = interview.getTagList();
				List<String> subcategory_names = tagList.stream().map(tag -> tag.getSubCategory().getName()).collect(Collectors.toList());
				//DTO 반환
				return InterviewListDto.builder()
					.id(interview.getId())
					.createDate(interview.getCreateDate())
					.title(interview.getTitle())
					.intro_text(interview.getIntro_text())
					.photo_url(interview.getPhoto_url())
					.view(interview.getView())
					.subcategory_names(subcategory_names)
					.build();
			}).collect(Collectors.toList());

		return new PageImpl<>(interviewListDtos);
	}

	// 인터뷰 하나 저장
	public void registerInterview(InterviewForm form){
		Interview interview = Interview.builder()
			.view(0)
			.video_url(form.getVideo_url())
			.photo_url(form.getPhoto_url())
			.title(form.getTitle())
			.sub_title(form.getSub_title())
			.intro_text(form.getIntro_text())
			.body_text(form.getBody_text()).build();
		Interview savedInterview = interviewRepository.save(interview);

		//toplike 연관
		TopLike topLike = TopLike.builder().total(0).likeWeek(0).likeToday(0).likePastday(0).build();
		topLike.setInterview(savedInterview);
		topLikeRepository.save(topLike);

		//tag 저장 및 연관
		List<Long> idList = form.getSubCategoryIdList();
		for (Long subId : idList) {
			Optional<SubCategory> sub = subCategoryRepository.findById(subId);
			sub.ifPresent(subCategory -> {
				Tag setTag = Tag.builder().interview(savedInterview).subCategory(subCategory).build();
				tagRepository.save(setTag);
			});
		}
	}
}
