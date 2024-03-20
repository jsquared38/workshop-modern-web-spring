package com.capgemini.recruitment.workshop.modernwebdev.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BlogPostEntity {
    @Id
    private String id;
    private String title;
    private String summary;
    private String postContent;
    private LocalDate createdDate;
}
