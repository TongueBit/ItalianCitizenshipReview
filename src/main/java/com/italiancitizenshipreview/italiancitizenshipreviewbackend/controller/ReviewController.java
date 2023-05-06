package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ReviewService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review")
    public void createReview(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @RequestParam("userId") Long userId,
                             @RequestParam("serviceProviderId") Long serviceProviderId,
                             @RequestParam("rating") int rating) {
        Review review = reviewService.createReview(title, content, userId, serviceProviderId, rating);
    }

    @GetMapping("/review")
    public String getUserId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("JSESSIONID")) {
                String jsessionid = cookie.getValue();
                System.out.println(jsessionid);
                break;
            }


        }
        return "home";
    }
}
