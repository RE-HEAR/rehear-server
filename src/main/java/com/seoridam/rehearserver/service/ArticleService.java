package com.seoridam.rehearserver.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seoridam.rehearserver.domain.Article;
import com.seoridam.rehearserver.dto.ArticleForm;
import com.seoridam.rehearserver.domain.SubCategory;
import com.seoridam.rehearserver.domain.Tag;
import com.seoridam.rehearserver.dto.ArticleProjection;
import com.seoridam.rehearserver.dto.ArticleResponseDto;
import com.seoridam.rehearserver.repository.ArticleRepository;
import com.seoridam.rehearserver.repository.SubCategoryRepository;
import com.seoridam.rehearserver.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
	private final ArticleRepository articleRepository;
	private final TagRepository tagRepository;
	private final SubCategoryRepository subCategoryRepository;

	// article 하나 조회
	@Transactional(readOnly = true)
	public ArticleResponseDto getArticle(Long id) {

		Optional<Article> found = articleRepository.findById(id);
		Article article = found.orElseThrow(RuntimeException::new);

		List<Tag> tagList = article.getTagList();
		List<String> subcategoryNames = tagList.stream().map(tag -> tag.getSubCategory().getName()).collect(Collectors.toList());

		return ArticleResponseDto.builder()
			.createDate(article.getCreateDate())
			.bodyText(article.getBodyText())
			.introText(article.getIntroText())
			.subTitle(article.getSubTitle())
			.photoUrl(article.getPhotoUrl())
			.subcategoryNames(subcategoryNames)
			.view(article.getView())
			.title(article.getTitle()).build();
	}

	//article 리스트 목록 조회
	@Transactional(readOnly = true)
	public Page<ArticleProjection> getArticleList(PageRequest pageRequest){
		return articleRepository.findArticleProjectionsBy(pageRequest);
	}

	// article 저장
	public Long registerArticle(ArticleForm form){
		Article article = Article.builder()
			.view(0)
			.photoUrl(form.getPhotoUrl())
			.title(form.getTitle())
			.subTitle(form.getSubTitle())
			.introText(form.getIntroText())
			.bodyText(form.getBodyText()).build();
		Article savedArticle = articleRepository.save(article);

		//관련 tag 저장
		List<Long> idList = form.getSubCategoryIdList();
		for (Long subId : idList) {
			Optional<SubCategory> sub = subCategoryRepository.findById(subId);
			sub.ifPresent(subCategory -> {
				Tag setTag = Tag.builder().article(savedArticle).subCategory(subCategory).build();
				tagRepository.save(setTag);
			});
		}
		return savedArticle.getId();
	}
}
