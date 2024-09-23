package me.leechanghwan.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;

import me.leechanghwan.springbootdeveloper.domain.Article;
import me.leechanghwan.springbootdeveloper.dto.AddArticleRequest;
import me.leechanghwan.springbootdeveloper.dto.ArticleResponse;
import me.leechanghwan.springbootdeveloper.dto.UpdateArticleRequest;
import me.leechanghwan.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;

    // 블로그글 생성하는 BlogService의 sava()메서드 호출한 뒤,
    // 생성된 블로그 글을 반환하는 작업을 할 addArticle() 메서드 작성
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    //전체글을 조회한 뒤 반환하는 findAllArticles() 메서드 추가

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    // /api/article.html/{id} GET 요청이 오면 블로그 글을 조회하기 위해
    // 매핑할 findArticle() 메서드 추가
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    // /api/articles/{id} DELETE 요청이 오면 글을 삭제하기 위해
    // 매핑할 findArticles() 메서드 추가
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    // /api/articles/{id} PUT 요청이 오면 글을 수정하기 위해
    // 매핑할 updateArticle()메서드 추가
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }


}