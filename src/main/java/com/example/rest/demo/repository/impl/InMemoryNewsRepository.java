package com.example.rest.demo.repository.impl;

import com.example.rest.demo.exception.EntityNotFoundException;
import com.example.rest.demo.model.News;
import com.example.rest.demo.model.User;
import com.example.rest.demo.repository.NewsRepository;
import com.example.rest.demo.repository.UserRepository;
import com.example.rest.demo.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

//@Repository
//public class InMemoryNewsRepository implements NewsRepository {
//
//    private UserRepository userRepository;
//    private final Map<Long, News> repository = new ConcurrentHashMap<>();
//
//    private final AtomicLong currentId = new AtomicLong(1);
//
//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public List<News> findAll() {
//        return new ArrayList<>(repository.values());
//    }
//
//    @Override
//    public Optional<News> findById(Long id) {
//        return Optional.ofNullable(repository.get(id));
//    }
//
//    @Override
//    public News save(News news) {
//        Long newsId = currentId.getAndIncrement();
//        Long userId = news.getUser().getId();
//        User user = userRepository.findById(userId)
//                .orElseThrow(()-> new EntityNotFoundException("ddd"));
//        news.setUser(user);
//        news.setId(newsId);
//        Instant now = Instant.now();
//        news.setCreateAt(now);
//        news.setUpdateAt(now);
//        repository.put(newsId,news);
//        user.addNews(news);
//        userRepository.update(user);
//        return news;
//    }
//
//    @Override
//    public News update(News news) {
//        Long newsId = news.getId();
//        Instant now = Instant.now();
//        News currentNews = repository.get(news);
//
//        if (currentNews == null){
//            throw new EntityNotFoundException("sda");
//        }
//        BeanUtils.copyNonNullProperties(news, currentNews);
//        currentNews.setUpdateAt(now);
//        currentNews.setId(newsId);
//        return currentNews;
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        repository.remove(id);
//    }
//
//    @Override
//    public void deleteByIdIn(List<Long> ids) {
//        ids.forEach(repository::remove);
//    }
//}
