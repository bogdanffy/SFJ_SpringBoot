package com.example._demo_app.repository;

import com.example._demo_app.domain.Blogger;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BloggerRepository extends CrudRepository<Blogger, Long> {

    @Override
    List<Blogger> findAll(); //A JDBC SELECT * FROM... függvényét használja
}
