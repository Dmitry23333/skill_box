//package com.example.rest.demo.repository.impl;
//
//import com.example.rest.demo.exception.EntityNotFoundException;
//import com.example.rest.demo.model.News;
//import com.example.rest.demo.model.User;
//import com.example.rest.demo.repository.NewsRepository;
//import com.example.rest.demo.repository.UserRepository;
//import com.example.rest.demo.utils.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicLong;
//import java.util.stream.Collectors;
//
//@Repository
//public class InMemoryUserRepository implements UserRepository {
//
//    private final Map<Long, User> repository = new ConcurrentHashMap<>();
//
//    private final AtomicLong currentId = new AtomicLong(1);
//
//    private NewsRepository newsRepository;
//
//    @Override
//    public List<User> findAll() {
//        return new ArrayList<>(repository.values());
//    }
//
//    @Override
//    public Optional<User> findById(Long id) {
//        return Optional.ofNullable(repository.get(id));
//    }
//
//    @Override
//    public User save(User user) {
//        Long userId = currentId.getAndIncrement();
//        user.setId(userId);
//        repository.put(userId, user);
//        return user;
//    }
//
//    @Override
//    public User update(User user) {
//        Long userId = user.getId();
//        User currentUser = repository.get(userId);
//        if (currentUser == null) {
//            throw new EntityNotFoundException("fff");
//        }
//
//        BeanUtils.copyNonNullProperties(user, currentUser);
//        currentUser.setId(userId);
//        repository.put(userId, currentUser);
//        return currentUser;
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        User user = repository.get(id);
//        if (user == null) {
//            throw new EntityNotFoundException("fff");
//        }
//        newsRepository.deleteByIdIn(user.getNewsList().stream().map(News::getId).collect(Collectors.toList()));
//        repository.remove(id);
//    }
//
//    @Autowired
//    public void setNewsRepository(NewsRepository newsRepository) {
//        this.newsRepository = newsRepository;
//    }
//}

