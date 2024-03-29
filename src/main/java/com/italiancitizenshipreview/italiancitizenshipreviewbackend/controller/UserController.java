package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.User;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ReviewService;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/user/dashboard/")
    public String getUserDashboard(Model model, @CookieValue (value = "userId") Long userId, HttpServletRequest request) {
        User user = userService.findUserById(userId);
        List<Review> reviews = reviewService.findAllReviewsByUserId(userId);
        model.addAttribute("reviews", reviews);
        model.addAttribute("user", user);
        model.addAttribute("request", request);
        return "user";
    }

    @PostMapping("/user/delete/{userId}")
    public String deleteAccount(@PathVariable Long userId) {
        userService.deleteUserById(userId);

        return "redirect:/logout";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/dashboard/{userId}")
    public String editAccount(@PathVariable Long userId, Model model, HttpServletRequest request) {
        User user = userService.findUserById(userId);
        List<Review> reviews = reviewService.findAllReviewsByUserId(userId);
        model.addAttribute("reviews", reviews);
        model.addAttribute("user", user);
        model.addAttribute("request", request);
        return "user";
    }
}
