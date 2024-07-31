package com.example.serviceexercise.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotNull(message = "id should not be null")
    private int id;

    @NotNull(message = "title should not be null")
    @Size(min = 4 ,max = 100, message = "title should not be more than 100 character")
    private String title;

    @NotNull(message = "author should not be null")
    @Size(min = 4, max =20 , message = "Name should be from 4 to 20 characters long")
    private String author;

    @NotNull(message = "content should not be null")
    @Size(min = 200, message = "content must be 200 characters or more")
    private String content;

    @NotNull(message = "category should not be null")
    @Pattern(regexp = "politics|sports|technology", message = "Category must be either 'politics', 'sports', or 'technology'")
    private String category;

    @NotNull(message = "imageUrl should not be null")
    private String imageUrl;

    @AssertFalse
    private boolean isPublished;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate publishDate;
}
