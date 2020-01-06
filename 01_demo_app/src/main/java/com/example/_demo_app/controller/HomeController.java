package com.example._demo_app.controller;

import com.example._demo_app.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    StoryService storyS;

    @Autowired
    public void setStoryS(StoryService storyS) {
        this.storyS = storyS;
    }

    @RequestMapping("/")
    public String stories(Model model){
        model.addAttribute("pageTitle", "Minden napra egy SFJ sztori.");
        model.addAttribute("stories", storyS.getStories());
        return "stories";
    }

    @RequestMapping("title/{title}")
    public String searchForUser(@PathVariable(value = "title") String title, Model model) throws Exception {
        if(title.equals("null")){
            throw new Exception("Nincs ilyen című bejegyzés!");
        }
        model.addAttribute(storyS.getSpecificStory(title));

        return "story";
    }

    @RequestMapping("newstory")
    public String showNewestStory(Model model){
        model.addAttribute("pageTitle", "A legfrissebb történet");
        model.addAttribute("story", storyS.getNewestStory());

        return "story";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(HttpServletRequest rA, Exception ex, Model model){
        model.addAttribute("errMessage", ex.getMessage());
        return "exceptionHandler";
    }


}
