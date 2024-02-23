package com.example.rest.demo.mapper.v2;

import com.example.rest.demo.model.News;
import com.example.rest.demo.web.model.NewsListResponse;
import com.example.rest.demo.web.model.NewsResponse;
import com.example.rest.demo.web.model.UpsertNewsRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
@DecoratedWith(NewsMapperDelegate.class)
public interface NewsMapperV2 {

    News requestToNews(UpsertNewsRequest request);

    @Mapping(source = "newsId", target = "id")
    News requestToNews(Long newsId, UpsertNewsRequest request);


    NewsResponse newsToResponse(News news);

    List<NewsResponse> newsListToResponseList(List<News> newsList);

    default NewsListResponse newsListToOrderListResponse(List<News> newsList) {
        NewsListResponse response = new NewsListResponse();
        response.setNewsList(newsListToResponseList(newsList));
        return response;
    }
}
