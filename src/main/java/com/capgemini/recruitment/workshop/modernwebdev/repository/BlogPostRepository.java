package com.capgemini.recruitment.workshop.modernwebdev.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.recruitment.workshop.modernwebdev.entity.BlogPostEntity;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPostEntity, String> {

    List<BlogPostEntity> findAllByTitleContainingIgnoreCaseOrPostContentContainingIgnoreCase(
            String title, String postContent);
}
