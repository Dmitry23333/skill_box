package com.example.rest.demo.mapper.v2;


import com.example.rest.demo.model.News;
import com.example.rest.demo.service.UserService;
import com.example.rest.demo.web.model.UpsertNewsRequest;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class NewsMapperDelegate implements NewsMapperV2 {


    private final UserService userService;


    @Override
    public News requestToNews(UpsertNewsRequest request) {
        News news = new News();
        news.setCost(request.getCost());
        news.setProduct(request.getProduct());
        news.setUser(userService.findById(request.getUserId()));
        return news;
    }

    @Override
    public News requestToNews(Long newsId, UpsertNewsRequest request) {
        News news = requestToNews(request);
        news.setId(newsId);
        return news;
    }
}
