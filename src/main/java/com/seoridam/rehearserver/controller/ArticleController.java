package com.seoridam.rehearserver.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seoridam.rehearserver.dto.ArticleForm;
import com.seoridam.rehearserver.dto.ArticleProjection;
import com.seoridam.rehearserver.dto.ArticleResponseDto;
import com.seoridam.rehearserver.dto.TagArticleProjection;
import com.seoridam.rehearserver.global.common.StatusEnum;
import com.seoridam.rehearserver.global.common.SuccessResponse;
import com.seoridam.rehearserver.service.ArticleService;
import com.seoridam.rehearserver.service.TagService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ArticleController {

	public final ArticleService articleService;
	public final TagService tagService;

	@GetMapping("/article/{id}")
	public SuccessResponse getArticle(@PathVariable Long id){

		ArticleResponseDto article = articleService.getArticle(id);

		return SuccessResponse.builder()
			.status(StatusEnum.OK)
			.data(article)
			.message("인터뷰 조회 성공")
			.build();
	}

	@GetMapping("/article/list")
	@ApiOperation(value = "내용을 제외한 인터뷰 리스트 목록 반환", notes = "ex: http://localhost:8080/article/list/?page=3&size=4")
	public SuccessResponse getArticleList(@RequestParam("page") Integer page, @RequestParam("size") Integer size){

		PageRequest pageRequest = PageRequest.of(page,size);
		Page<ArticleProjection> articleList = articleService.getArticleList(pageRequest);

		return SuccessResponse.builder()
			.status(StatusEnum.OK)
			.data(articleList)
			.message("인터뷰 조회 성공")
			.build();
	}

	@GetMapping("/article/list/subcategory/{subcategoryId}")
	@ApiOperation(value = "서브카테고리 id를 적용한 인터뷰 리스트 목록 반환", notes = "ex: http://localhost:8080/article/list/2/?page=3&size=4")
	public SuccessResponse getArticleListBySubcategoryId(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @PathVariable Long subcategoryId){

		PageRequest pageRequest = PageRequest.of(page,size);
		Page<TagArticleProjection> articleListBySubCategoryId = tagService.getArticleListBySubCategoryId(
			subcategoryId, pageRequest);

		return SuccessResponse.builder()
			.status(StatusEnum.OK)
			.data(articleListBySubCategoryId)
			.message("서브카테고리 별 인터뷰 조회 성공")
			.build();
	}

	@PostMapping("/admin/article")
	@ResponseStatus(HttpStatus.CREATED)
	public void registerArticle(@Valid @RequestBody ArticleForm articleForm){
		articleService.registerArticle(articleForm);
	}

}
