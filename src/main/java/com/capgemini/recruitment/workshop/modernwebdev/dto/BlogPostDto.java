package com.capgemini.recruitment.workshop.modernwebdev.dto;

import java.time.LocalDate;

public record BlogPostDto(String id, String title, String summary, String postContent, LocalDate createdDate) {}

