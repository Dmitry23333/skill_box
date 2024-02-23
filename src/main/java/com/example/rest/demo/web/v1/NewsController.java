package com.example.rest.demo.web.v1;

import com.example.rest.demo.mapper.v1.NewsMapper;
import com.example.rest.demo.model.News;
import com.example.rest.demo.service.NewsService;
import com.example.rest.demo.web.model.NewsListResponse;
import com.example.rest.demo.web.model.NewsResponse;
import com.example.rest.demo.web.model.UpsertNewsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    private final NewsMapper newsMapper;

    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(){
        return ResponseEntity.ok(newsMapper.newsListToOrderListResponse(newsService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity <NewsResponse> findById (@PathVariable Long id){
        return  ResponseEntity.ok(
                newsMapper.newsToResponse(newsService.findById(id))
        );
    }

    @PostMapping
    public  ResponseEntity <NewsResponse> create(@RequestBody UpsertNewsRequest request){
        News news1 = newsService.save(newsMapper.requestToNews(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(newsMapper.newsToResponse(news1));
    }

    @PutMapping("/{id}")
    public ResponseEntity <NewsResponse> update(@PathVariable Long id,
                                                 @RequestBody UpsertNewsRequest request){
        News updatedNews = newsService.update(newsMapper.requestToNews(id,request));
        return ResponseEntity.ok(newsMapper.newsToResponse(updatedNews));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete (@PathVariable Long id){
        newsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
