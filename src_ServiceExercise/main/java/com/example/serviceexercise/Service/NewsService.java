package com.example.serviceexercise.Service;

import com.example.serviceexercise.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class NewsService {
    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    public ArrayList<NewsArticle> getNewsArticles(){
        return newsArticles;
    }
    public void addNewsArticle(NewsArticle newsArticle){
        newsArticles.add(newsArticle);
    }
    public boolean updateNewsArticle(int id, NewsArticle newsArticle){
        for(int i = 0; i<newsArticles.size(); i++){
           if(newsArticles.get(i).getId() == id){
               newsArticles.set(i,newsArticle);
               return true;
           }
        }
        return false;
    }
    public boolean deleteNewsArticle(int id){
        for(int i = 0; i<newsArticles.size(); i++){
            if(newsArticles.get(i).getId() == id){
                newsArticles.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean publishNewsArticle(int id) { // change isPublished to true by id
        for(int i = 0; i<newsArticles.size(); i++){
            if(newsArticles.get(i).getId() == id){
                NewsArticle article = newsArticles.get(i);
                article.setPublished(true);
                return true;
            }
        }
        return false;
    }
    public ArrayList<NewsArticle> getPublishedNewsArticles(){
        ArrayList<NewsArticle> publishedNews = new ArrayList<>();
        for(NewsArticle article : newsArticles){
            if(article.isPublished()){
                publishedNews.add(article);
            }
        }
        return publishedNews;
    }
    public ArrayList<NewsArticle> getNewsByCategory(String category){
        ArrayList<NewsArticle> categorizedNews = new ArrayList<>();
        for(NewsArticle article : newsArticles){
            if(article.getCategory().equalsIgnoreCase(category)){
                categorizedNews.add(article);
            }
        }
        return categorizedNews;
    }
}


