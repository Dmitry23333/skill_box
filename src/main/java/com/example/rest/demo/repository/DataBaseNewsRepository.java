package com.example.rest.demo.repository;

import com.example.rest.demo.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DataBaseNewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor <News> {

    //Page<Order> findAllByProduct (String product, Pageable pageable);

}
