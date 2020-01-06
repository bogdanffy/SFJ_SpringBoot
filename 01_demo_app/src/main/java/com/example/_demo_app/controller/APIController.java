package com.example._demo_app.controller;

import com.example._demo_app.domain.Story;
import com.example._demo_app.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class APIController {
    private StoryService storyS;

    @Autowired
    public void setStoryS(StoryService storyS) {
        this.storyS = storyS;
    }

    @RequestMapping("api/title/{title}")
    private Story getStoryByTitle(@PathVariable(value = "title") String title) throws Exception {
        if(title.equals("null")){
            throw new Exception("Nincs ilyen című bejegyzés!");
        }

        return storyS.getSpecificStory(title);
    }

    @RequestMapping("api/newstory")
    private Story getNewestStory(){
        return storyS.getNewestStory();
    }

    @RequestMapping("/api")
    private List<Story> getAllStory(){
        return storyS.getStories();
    }

    @RequestMapping("/api/blogger/{name}")
    private List<Story> getAllStory(@PathVariable("name") String name) throws Exception {
        if(name.equals("null")){
            throw new Exception("Nincs ilyen című bejegyzés!");
        }
        return storyS.getStoriesByName(name);
    }

}
