package me.leechanghwan.springbootdeveloper.repository;

import me.leechanghwan.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}