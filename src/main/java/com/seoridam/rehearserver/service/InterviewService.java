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
import com.seoridam.rehearserver.domain.Tag;
import com.seoridam.rehearserver.dto.InterviewListDto;
import com.seoridam.rehearserver.dto.InterviewListSource;
import com.seoridam.rehearserver.dto.InterviewResponseDto;
import com.seoridam.rehearserver.repository.InterviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class InterviewService {
	private final InterviewRepository interviewRepository;

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
	public Page<InterviewListSource> getInterviewList(PageRequest pageRequest){
		return interviewRepository.findInterviewProjectionsBy(pageRequest);
	}
}
