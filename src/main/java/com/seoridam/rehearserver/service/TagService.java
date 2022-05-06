package com.seoridam.rehearserver.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.seoridam.rehearserver.dto.TagArticleProjection;
import com.seoridam.rehearserver.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {
	private final TagRepository tagRepository;

	public Page<TagArticleProjection> getArticleListBySubCategoryId(Long subcategoryId, PageRequest pageRequest){
		return tagRepository.findArticleListBySubCategoryId(subcategoryId, pageRequest);
	}
}
