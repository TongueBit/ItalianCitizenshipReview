package com.italiancitizenshipreview.italiancitizenshipreviewbackend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.Review;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.ServiceProvider;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ReviewRepository;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.KeyValue;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ServiceProviderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class LandingPageController {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/")
    public String landingPageRedirect() {
        return "redirect:/index";
    }
    @GetMapping("/index")
    public String homePage(Model model, HttpServletRequest request) {
        /**Map<String, KeyValue> mapping = new HashMap<>();
        mapping.put("italymondo", new KeyValue("ItalyMondo!", 1));
        mapping.put("FullCircleCitizenship",new KeyValue("Full Circle Citizenship", 20) );
        mapping.put("007italianrecords", new KeyValue("007 Italian Records", 11));
        mapping.put("BellaItaliaGenealogy", new KeyValue("Bella Italia Genealogy",  15));

        mapping.put("ItalianDualCitizenship", new KeyValue("Italian Dual Citizenship", 3));
        mapping.put("futuracidadaniaitaliana", new KeyValue("Futura Cidadania Italiana", 24));
        mapping.put("itamcap", new KeyValue("Italian American Citizenship Assistance Program", 13));
        mapping.put("LoSchiavoGenealogica", new KeyValue("Lo Schiavo Genealogica", 19));
        mapping.put("italiancitizenshipassistance", new KeyValue("Italian Citizenship Assistance", 2));
        mapping.put("richrootsgenealogy", new KeyValue("Rich Roots Genealogy",  18));
        mapping.put("ItalianPapersItaly", new KeyValue("Italian Papers", 31));
        mapping.put("lorenzonidualcitizenship", new KeyValue("Lorenzoni Dual Citizenship Services", 32));

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read the JSON data from a file
            JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/dataset_facebook-reviews-scraper_2023-08-23_15-00-01-955.json"));

            // Iterate through each JSON object in the array
            for (JsonNode jsonObject : jsonNode) {

                String pageName = jsonObject.get("pageName").asText();

                JsonNode user = jsonObject.get("user");
                String userName = user.get("name").asText();
                String userProfilePic = user.get("profilePic").asText();
                String userDateStr = jsonObject.get("date").asText();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust the format as needed

                Long id = (long) mapping.get(pageName).getServiceProviderId();
                ServiceProvider serviceProvider = serviceProviderService.getServiceProvider(id);

                try {
                    // Parse the string and convert it to a Date object
                    Date userDate = dateFormat.parse(userDateStr);
                    String userText = jsonObject.has("text") ? jsonObject.get("text").asText() : "";
                    Boolean isRecommended = jsonObject.has("isRecommended") ? jsonObject.get("isRecommended").asBoolean() : false;
                    Review review = new Review(userName, userDate, userText, isRecommended, 0, serviceProvider);
                    reviewRepository.save(review);
                    // Now, userDate is a Date object representing the date from the JSON string
                } catch (ParseException e) {
                    e.printStackTrace(); // Handle the exception if the date string is in an invalid format
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }*/
        model.addAttribute("request", request);
        return "index";
    }


}
