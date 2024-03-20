package com.capgemini.recruitment.workshop.modernwebdev.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.capgemini.recruitment.workshop.modernwebdev.dto.BlogPostDto;
import com.capgemini.recruitment.workshop.modernwebdev.entity.BlogPostEntity;
import com.capgemini.recruitment.workshop.modernwebdev.repository.BlogPostRepository;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public BlogPostDto createBlogPost(BlogPostDto blogPostDto) {
        return createOrUpdateBlogPost(blogPostDto);
    }

    public BlogPostDto updateBlogPost(BlogPostDto blogPostDto) {
        return createOrUpdateBlogPost(blogPostDto);
    }

    public List<BlogPostDto> readAllBlogPosts() {
        return blogPostRepository.findAll()
                                 .stream()
                                 .map(this::mapToBlogPostDto)
                                 .toList();
    }

    public BlogPostDto readBlogPostById(String id) {
        return blogPostRepository.findById(id)
                                 .map(this::mapToBlogPostDto)
                                 .orElseThrow(() -> new IllegalArgumentException("Cannot find what you are looking for with id: {id}"));
    }

    public List<BlogPostDto> readAllByTitleOrPostContent(String title,
                                                         String postContent) {
        return blogPostRepository.findAllByTitleContainingIgnoreCaseOrPostContentContainingIgnoreCase(title,
                                                                                                      postContent)
                                 .stream()
                                 .map(this::mapToBlogPostDto)
                                 .toList();
    }

    public void deleteBlogPostById(String id) {
        blogPostRepository.deleteById(id);
    }

    private BlogPostDto mapToBlogPostDto(BlogPostEntity blogPostEntity) {
        return new BlogPostDto(blogPostEntity.getId(),
                               blogPostEntity.getTitle(),
                               blogPostEntity.getSummary(),
                               blogPostEntity.getPostContent(),
                               blogPostEntity.getCreatedDate());
    }

    private BlogPostDto createOrUpdateBlogPost(BlogPostDto blogPostDto) {
        var blogPostEntity = BlogPostEntity.builder()
                                           .id((blogPostDto.id() == null || blogPostDto.id()
                                                                                       .isEmpty())
                                               ? UUID.randomUUID()
                                                     .toString()
                                               : blogPostDto.id())
                                           .title(blogPostDto.title())
                                           .summary(blogPostDto.summary())
                                           .postContent(blogPostDto.postContent())
                                           .createdDate(blogPostDto.createdDate() != null
                                                        ? blogPostDto.createdDate()
                                                        : LocalDate.now())
                                           .build();

        final BlogPostEntity savedBlogPostEntity = blogPostRepository.save(blogPostEntity);

        return new BlogPostDto(savedBlogPostEntity.getId(),
                               savedBlogPostEntity.getTitle(),
                               savedBlogPostEntity.getSummary(),
                               savedBlogPostEntity.getPostContent(),
                               savedBlogPostEntity.getCreatedDate());
    }

}
