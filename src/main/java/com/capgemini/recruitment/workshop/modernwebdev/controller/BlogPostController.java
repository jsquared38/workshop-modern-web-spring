package com.capgemini.recruitment.workshop.modernwebdev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.recruitment.workshop.modernwebdev.dto.BlogPostDto;
import com.capgemini.recruitment.workshop.modernwebdev.service.BlogPostService;

@RestController
@RequestMapping("/blogs/posts")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public List<BlogPostDto> readAllBlogPosts(@RequestParam(required = false) String title,
                                              @RequestParam(required = false) String description) {
        final List<BlogPostDto> blogPostDtos;

        if (title != null || description != null) {
            blogPostDtos = blogPostService.readAllByTitleOrPostContent(title,
                                                                       description);
        } else {
            blogPostDtos = blogPostService.readAllBlogPosts();
        }

        return blogPostDtos;
    }

    @PostMapping
    public BlogPostDto createBlogPost(@RequestBody BlogPostDto blogPostDto) {
        return blogPostService.createBlogPost(blogPostDto);
    }

    @PutMapping
    public BlogPostDto updateBlogPost(@RequestBody BlogPostDto blogPostDto) {
        return blogPostService.updateBlogPost(blogPostDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBlogPost(@PathVariable String id) {
        blogPostService.deleteBlogPostById(id);
    }
}
