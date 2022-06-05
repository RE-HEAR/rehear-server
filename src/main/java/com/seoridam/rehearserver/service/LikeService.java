package com.seoridam.rehearserver.service;

import com.seoridam.rehearserver.domain.Article;
import com.seoridam.rehearserver.domain.Like;
import com.seoridam.rehearserver.domain.User;
import com.seoridam.rehearserver.dto.LikeRequestDto;
import com.seoridam.rehearserver.global.common.LikeTypeEnum;
import com.seoridam.rehearserver.repository.ArticleRepository;
import com.seoridam.rehearserver.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final ArticleRepository articleRepository;

    public void postLike(LikeRequestDto dto, User user){

        if(user == null)
            throw new IllegalArgumentException("사용자 인증 정보가 부족합니다.");

        Optional<Article> article = articleRepository.findById(dto.getArticleId());
        if(article.isEmpty())
            throw new IllegalArgumentException("해당 인터뷰 게시글이 없습니다.");

        Like like = Like.builder()
                .article(article.get())
                .user(user)
                .likeType(dto.getLikeType())
                .build();

        if(checkIsLikeExist(user.getId(), dto.getArticleId())){
            if(!checkIsLikeTypeEqual(user.getId(), dto.getArticleId(), dto.getLikeType()))
                updateLikeType(user.getId(), dto.getArticleId(), dto.getLikeType());
            else
                likeRepository.deleteByUserIdAndArticleId(user.getId(), dto.getArticleId());
        }
        else
            likeRepository.save(like);
    }

    private boolean checkIsLikeExist(long userId, long articleId) {
        return likeRepository.findByUserIdAndArticleId(userId, articleId).isPresent();
    }

    private boolean checkIsLikeTypeEqual(long userId, long articleId, LikeTypeEnum likeType) {
        return likeRepository.findByUserIdAndArticleId(userId, articleId).get().getLikeType().equals(likeType);
    }

    @Transactional
    public void updateLikeType(long userId, long articleId, LikeTypeEnum likeType){
        Optional<Like> like = likeRepository.findByUserIdAndArticleId(userId, articleId);
        like.ifPresent(selectLike->{
            selectLike.setLikeType(likeType);
            likeRepository.save(selectLike);
        });
    }
}
