package com.example._demo_app.repository;

import com.example._demo_app.domain.Story;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoryRepository extends CrudRepository<Story, Long> {

    @Override
    List<Story> findAll(); //A JDBC SELECT * FROM... függvényét használja

    //SELECT * FROM STORIES WHERE posted IN (SELECT max(POSTED) FROM stories) LIMIT 1;
    Story findFirstByOrderByPostedDesc();

    //SELECT * FROM STORIES WHERE title LIKE "titleValue" LIMIT 1;
    Story findByTitle(String title);

    //SELECT * FROM STORIES LEFT JOIN BLOGGER WHERE BLOGGER.name LIKE :name;
    List<Story> findAllByBloggerNameIgnoreCaseOrderByPostedDesc(String name);

    @Query(value = "SELECT * FROM STORIES s LEFT JOIN BLOGGER b WHERE UPPER(b.name) LIKE UPPER(:name)", nativeQuery = true)
    List<Story> findByBlogger(@Param("name") String name);
}
