package com.example._demo_app.service;

import com.example._demo_app.domain.Blogger;
import com.example._demo_app.domain.Story;
import com.example._demo_app.repository.BloggerRepository;
import com.example._demo_app.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
public class StoryService {

    StoryRepository storyRepository;
    BloggerRepository bloggerRepository;

    @Autowired
    public void setStoryRepository(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Autowired
    public void setBloggerRepository(BloggerRepository bloggerRepository) {
        this.bloggerRepository = bloggerRepository;
    }

    public List<Story> getStories() {
        List<Story> stories = storyRepository.findAll();

        return stories;
    }

    public Story getNewestStory() {
        Story newestStory = storyRepository.findFirstByOrderByPostedDesc();
        return newestStory;
    }

    public Story getSpecificStory(String title) {
        Story specificStory = storyRepository.findByTitle(title);
        return specificStory;
    }

    public List<Story> getStoriesByName(String name) {
        //List<Story> storiesByName = storyRepository.findAllByBloggerNameIgnoreCaseOrderByPostedDesc(name);

        List<Story> storiesByName = storyRepository.findByBlogger(name);

        return storiesByName;
    }

    //A StoryService létrejöttekor hívja meg 1x ezt a metódust, frissítéskor sem ismétlődik meg
    //Nem kell meghívni sem
    @PostConstruct
    public void init() {
        Blogger blogger = new Blogger("Karesz a belsős", 33);
        bloggerRepository.save(blogger); //Először ezt kell lementeni, hogy utána átadhassuk a story-nak

        Story story = new Story("A NAGY dobás", "Amúgy múltkor dobtam egy hatalmasat, aztán mondom az igen... Ezt majd leírom.", new Date(), blogger);
        storyRepository.save(story);
    }
}
