package com.patdimby.simplerest.controller;

import com.patdimby.simplerest.dto.UserDto;
import com.patdimby.simplerest.model.*;
import com.patdimby.simplerest.service.*;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/blog")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@CrossOrigin
//@AllArgsConstructor
public class MainController {
   
    @Autowired
	private final BlogService blogService;
	
	@Autowired
	private final EventService eventService;
	
	@Autowired
	private final TeamService teamService;
	
	@Autowired
    private final UserService userService;
	
	@Autowired
    private final ResumeService resumeService;

    public MainController(BlogService blogService, EventService eventService, TeamService teamService, UserService userService, ResumeService resumeService) {
        this.blogService = blogService;
		this.eventService = eventService;
		this.teamService = teamService;
        this.userService = userService;
        this.resumeService = resumeService;
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        AddAttributes(model);
        model.addAttribute("title", "Blog - Home");
        return "blog/index";
    }

    private void AddAttributes(Model model) {
        List<Blog> blogs = blogService.getAllBlogs();
        model.addAttribute("blogs", blogs);
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        List<Resume> resumes = resumeService.getAllResumes();
        model.addAttribute("resumes", resumes);
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        AddAttributes(model);
        model.addAttribute("title", "Blog - Login");
        return "blog/login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        AddAttributes(model);
        model.addAttribute("user", new UserDto());
        model.addAttribute("title", "Blog - Register");
        return "blog/registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDto userDto) {
        userService.saveUserDto(userDto);
        return "redirect:blog/login";
    }
}
