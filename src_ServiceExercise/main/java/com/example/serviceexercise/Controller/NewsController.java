package com.example.serviceexercise.Controller;

import com.example.serviceexercise.Model.NewsArticle;
import com.example.serviceexercise.Service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/get")
    public ResponseEntity getNews() {
        ArrayList<NewsArticle>newsArticles = newsService.getNewsArticles();
        return ResponseEntity.status(200).body(newsArticles);
    }

    @PostMapping("/add")
    public ResponseEntity addNews(@Valid @RequestBody NewsArticle newsArticle, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        newsService.addNewsArticle(newsArticle);
        return ResponseEntity.status(201).body(newsArticle);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNews(@Valid @RequestBody NewsArticle newsArticle, @PathVariable int id, Errors errors) {
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = newsService.updateNewsArticle(id, newsArticle);
        if(isUpdated){
            return ResponseEntity.status(201).body("newsArticle updated");
        }
        return ResponseEntity.status(404).body("newsArticle not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNews(@PathVariable int id) {
        boolean isDeleted = newsService.deleteNewsArticle(id);
        if(isDeleted){
            return ResponseEntity.status(201).body("newsArticle deleted");
        }
        return ResponseEntity.status(400).body("newsArticle not found");
    }
    //to publish article this method will change isPublished to true and make it appear in the getPublishedNews method
    @GetMapping("/publish/{id}")
    public ResponseEntity publishArticle(@PathVariable int id) {
        boolean isPublished = newsService.publishNewsArticle(id);
        if(isPublished){
            return ResponseEntity.status(201).body("newsArticle published");
        }
        return ResponseEntity.status(404).body("newsArticle not found");
    }

    @GetMapping("/published")
    public ResponseEntity getPublishedNews() {
        ArrayList<NewsArticle> publishedNews = newsService.getPublishedNewsArticles();
        return ResponseEntity.status(200).body(publishedNews);
    }

    @GetMapping("/{category}")
    public ResponseEntity getNewsByCategory(@PathVariable String category) {
        ArrayList<NewsArticle> newsArticles = newsService.getNewsByCategory(category);
        if(newsArticles.isEmpty()){
            return ResponseEntity.status(404).body("category not found");
        }
        return ResponseEntity.status(200).body(newsArticles);
    }

}
