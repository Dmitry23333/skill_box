package com.example.rest.demo.service.impl;

import com.example.rest.demo.exception.EntityNotFoundException;
import com.example.rest.demo.model.News;
import com.example.rest.demo.model.User;
import com.example.rest.demo.repository.DataBaseNewsRepository;
import com.example.rest.demo.repository.DataBaseUserRepository;
import com.example.rest.demo.service.UserService;
import com.example.rest.demo.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataBaseUserService implements UserService {
    private final DataBaseUserRepository userRepository;

    private final DataBaseNewsRepository newsRepository;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format("Пользователь с id {0} не найден!", id)));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existedClient = findById(user.getId());
        BeanUtils.copyNonNullProperties(user,existedClient);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

 //   @Override
 //   @Transactional
//    public User saveWithOrders(User client, List<News> newsList) {
 //       U savedClient = userRepository.save(client);
//
 //       throw new RuntimeException();
   // }
}
