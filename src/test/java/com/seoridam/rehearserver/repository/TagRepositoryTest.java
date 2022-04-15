package com.seoridam.rehearserver.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.seoridam.rehearserver.dto.TagInterviewProjection;

@SpringBootTest
class TagRepositoryTest {
	@Autowired
	private TagRepository tagRepository;

	@Test
	public void 인터뷰조회() throws Exception {
		Page<TagInterviewProjection> projections = tagRepository.findInterviewListBySubCategoryId(1L, PageRequest.of(0,2));
		for (TagInterviewProjection projection : projections) {
			System.out.println("projection " + projection.getInterviewTitle());
		}
	}


}