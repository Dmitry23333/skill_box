package com.example.rest.demo.mapper.v1;

import com.example.rest.demo.model.News;
import com.example.rest.demo.service.UserService;
import com.example.rest.demo.web.model.NewsListResponse;
import com.example.rest.demo.web.model.NewsResponse;
import com.example.rest.demo.web.model.UpsertNewsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NewsMapper {
    private final UserService userService;

    public News requestToNews(UpsertNewsRequest request) {
        News news = new News();

        news.setCost(request.getCost());
        news.setProduct(request.getProduct());
        news.setUser(userService.findById(request.getUserId()));
        return news;
    }

    public News requestToNews(Long newsId, UpsertNewsRequest request) {
        News news = this.requestToNews(request);
        news.setId(newsId);
        return news;
    }

    public NewsResponse newsToResponse(News news) {
        NewsResponse newsResponse = new NewsResponse();
        newsResponse.setId(news.getId());
        newsResponse.setCost(news.getCost());
        newsResponse.setProduct(news.getProduct());
        return newsResponse;
    }

    public List<NewsResponse> newsListToResponseList (List <News> newsList){
        return newsList.stream().map(this::newsToResponse)
                .collect(Collectors.toList());
    }

    public NewsListResponse newsListToOrderListResponse (List <News> newsList){
        NewsListResponse response = new NewsListResponse();
        response.setNewsList(newsListToResponseList(newsList));
        return  response;
    }
}
