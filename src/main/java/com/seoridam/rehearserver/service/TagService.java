package com.seoridam.rehearserver.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.seoridam.rehearserver.dto.TagInterviewProjection;
import com.seoridam.rehearserver.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {
	private final TagRepository tagRepository;

	public Page<TagInterviewProjection> getInterviewListBySubCategoryId(Long subcategoryId, PageRequest pageRequest){
		return tagRepository.findInterviewListBySubCategoryId(subcategoryId, pageRequest);
	}
}
