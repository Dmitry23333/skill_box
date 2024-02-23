package com.example.rest.demo.service.impl;

import com.example.rest.demo.exception.EntityNotFoundException;
import com.example.rest.demo.model.News;
import com.example.rest.demo.model.User;
import com.example.rest.demo.repository.DataBaseNewsRepository;
import com.example.rest.demo.service.NewsService;
import com.example.rest.demo.service.UserService;
import com.example.rest.demo.utils.BeanUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataBaseNewsService implements NewsService {
    private final DataBaseNewsRepository newsRepository;
    private final UserService userService;


    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Новость с id {0} не найден!", id)));
    }

    @Override
    public News save(News order) {
        User user = userService.findById(order.getUser().getId());
        order.setUser(user);
        return newsRepository.save(order);
    }

    @Override
    public News update(News news) {
        User user = userService.findById(news.getUser().getId());
        News existedNews = findById(news.getId());

        BeanUtils.copyNonNullProperties(news, existedNews);
        existedNews.setUser(user);
        return newsRepository.save(existedNews);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        newsRepository.deleteAllById(ids);
    }
}
