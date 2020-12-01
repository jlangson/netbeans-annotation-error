/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tts.TechTalentTwitter.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author jared
 */
@Controller
public class FollowController {
    @Autowired
    private UserService userService;
    
    @PostMapping(value = "/follow/{username}")
    public String follow(@PathVariable(value="username") String username, 
                         HttpServletRequest request) {
    	User loggedInUser = userService.getLoggedInUser();
    	User userToFollow = userService.findByUsername(username);
    	List<User> followers = userToFollow.getFollowers();
    	followers.add(loggedInUser);
    	userToFollow.setFollowers(followers);
        userService.save(userToFollow);
        return "redirect:" + request.getHeader("Referer");
    	}
    
    @PostMapping(value = "/unfollow/{username}")
public String unfollow(@PathVariable(value="username") String username, HttpServletRequest request) {
    User loggedInUser = userService.getLoggedInUser();
    User userToUnfollow = userService.findByUsername(username);
    List<User> followers = userToUnfollow.getFollowers();
    followers.remove(loggedInUser);
    userToUnfollow.setFollowers(followers);
    userService.save(userToUnfollow);
    return "redirect:" + request.getHeader("Referer");
    }    
}

