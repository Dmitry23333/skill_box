package com.example.rest.demo.repository;


import com.example.rest.demo.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataBaseUserRepository extends JpaRepository <User,Long> {


}
