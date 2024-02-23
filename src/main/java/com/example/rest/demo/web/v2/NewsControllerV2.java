package com.example.rest.demo.web.v2;

import com.example.rest.demo.mapper.v2.NewsMapperV2;
import com.example.rest.demo.model.News;
import com.example.rest.demo.service.NewsService;
import com.example.rest.demo.web.model.NewsListResponse;
import com.example.rest.demo.web.model.NewsResponse;
import com.example.rest.demo.web.model.UpsertNewsRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/news")
@RequiredArgsConstructor
public class NewsControllerV2 {
    private final NewsService newsService;

    private final NewsMapperV2 newsMapperV2;

  //  @GetMapping("/filter")
  //  public ResponseEntity<NewsListResponse> filterBy (@Valid NewsF filter){
  //      return ResponseEntity.ok(
  //              orderMapper.orderListToOrderListResponse(newsService.filterBy(
  //                      filter
  //              ))
  //      );
  //  }


    @GetMapping
    public ResponseEntity <NewsListResponse> findAll (){
        return ResponseEntity.ok(
                newsMapperV2.newsListToOrderListResponse(
                        newsService.findAll()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity <NewsResponse>  findById (@PathVariable Long id){
        return ResponseEntity.ok(
                newsMapperV2.newsToResponse(
                        newsService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<NewsResponse> create (@RequestBody @Valid UpsertNewsRequest request){
        News newNews = newsService.save(newsMapperV2.requestToNews(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapperV2.newsToResponse(newNews));
    }

    @PutMapping("/{id}")
    public ResponseEntity <NewsResponse> update (@PathVariable("id") Long orderId, @RequestBody @Valid UpsertNewsRequest request){
        News updatedNews = newsService.update(newsMapperV2.requestToNews(orderId,request));

        return ResponseEntity.ok(newsMapperV2.newsToResponse(updatedNews));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        newsService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
