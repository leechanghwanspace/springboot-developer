package me.leechanghwan.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import me.leechanghwan.springbootdeveloper.domain.Article;
import me.leechanghwan.springbootdeveloper.dto.AddArticleRequest;
import me.leechanghwan.springbootdeveloper.dto.UpdateArticleRequest;
import me.leechanghwan.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    //블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    //모든 글 조회 메서드
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    //블로그 글 하나를 조회하는 메서드
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    //블로그 글 삭제 메서드
    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    //블로그 글을 수정하는 메서드
    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
