import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.service.ReviewService;

import java.io.File;
import java.io.IOException;

public class JsonParser {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read the JSON data from a file
            JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/dataset_facebook-reviews-scraper_2023-08-23_15-00-01-955.json"));
            int i = 0;
            // Iterate through each JSON object in the array
            for (JsonNode jsonObject : jsonNode) {
                i++;
                String pageName = jsonObject.get("pageName").asText();

                JsonNode user = jsonObject.get("user");
                String userName = user.get("name").asText();
                String userProfilePic = user.get("profilePic").asText();
                String userDate = jsonObject.get("date").asText();
                String userText = jsonObject.has("text") ? jsonObject.get("text").asText() : "";
                Boolean isRecommended = jsonObject.has("isRecommended") ? jsonObject.get("isRecommended").asBoolean() : false;

                // Extract the name part from the Facebook URL



                // Print the extracted data

                System.out.println("User Name: " + userName);
                System.out.println("User Profile Pic: " + userProfilePic);
                System.out.println("User Date: " + userDate);
                System.out.println("User Text: " + userText);
                System.out.println("Page Name: " + pageName);
                System.out.println("Is Recommended: " + isRecommended);
                System.out.println();
                System.out.println(i);
                ReviewService reviewService = new ReviewService();
                reviewService.saveReview(userName, userProfilePic, userDate, userText, pageName, isRecommended);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
