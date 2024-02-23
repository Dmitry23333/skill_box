//package com.example.rest.demo.service.impl;
//
//import com.example.rest.demo.exception.EntityNotFoundException;
//import com.example.rest.demo.model.News;
//import com.example.rest.demo.repository.NewsRepository;
//import com.example.rest.demo.service.NewsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class NewsServiceImpl implements NewsService {
//    private final NewsRepository newsRepository;
//
//    @Override
//    public List<News> findAll() {
//        return newsRepository.findAll();
//    }
//
//    @Override
//    public News findById(Long id) {
//        return newsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("dsad"));
//    }
//
//    @Override
//    public News save(News news) {
//        return newsRepository.save(news);
//    }
//
//    @Override
//    public News update(News news) {
//        return newsRepository.update(news);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        News currentNews = findById(id);
//        currentNews.getUser().removeNews(id);
//        newsRepository.deleteById(id);
//    }
//
//    @Override
//    public void deleteByIdIn(List<Long> ids) {
//        newsRepository.deleteByIdIn(ids);
//    }
//
//
//}
