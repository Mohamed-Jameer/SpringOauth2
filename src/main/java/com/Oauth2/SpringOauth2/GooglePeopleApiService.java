package com.Oauth2.SpringOauth2;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class GooglePeopleApiService {

    private static final String GOOGLE_PEOPLE_API_URL =
            "https://people.googleapis.com/v1/people/me?personFields=names,emailAddresses,photos,genders,addresses,phoneNumbers,birthdays";

    public Map<String, Object> fetchExtraDetails(String accessToken) {
        Map<String, Object> extraDetails = new HashMap<>();

        // 🟢 Create Headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 🟢 Make API Request
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(GOOGLE_PEOPLE_API_URL, HttpMethod.GET, request, String.class);

        // 🟢 Parse JSON Response
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());

            // ✅ Name
            JsonNode nameNode = rootNode.path("names");
            String name = nameNode.isArray() && nameNode.size() > 0 ? nameNode.get(0).path("displayName").asText() : "Not Available";
            extraDetails.put("name", name);

            // ✅ Email
            JsonNode emailNode = rootNode.path("emailAddresses");
            String email = emailNode.isArray() && emailNode.size() > 0 ? emailNode.get(0).path("value").asText() : "Not Available";
            extraDetails.put("email", email);

            // ✅ Profile Picture
            JsonNode photoNode = rootNode.path("photos");
            String picture = photoNode.isArray() && photoNode.size() > 0 ? photoNode.get(0).path("url").asText() : "Not Available";
            extraDetails.put("picture", picture);

            // ✅ Gender
            JsonNode genderNode = rootNode.path("genders");
            String gender = genderNode.isArray() && genderNode.size() > 0 ? genderNode.get(0).path("value").asText() : "Not Available";
            extraDetails.put("gender", gender);

            // ✅ Address
            JsonNode addressNode = rootNode.path("addresses");
            String address = addressNode.isArray() && addressNode.size() > 0 ? addressNode.get(0).path("formattedValue").asText() : "Not Available";
            extraDetails.put("address", address);

            // ✅ Phone Number
            JsonNode phoneNode = rootNode.path("phoneNumbers");
            String phone = phoneNode.isArray() && phoneNode.size() > 0 ? phoneNode.get(0).path("value").asText() : "Not Available";
            extraDetails.put("phoneNumber", phone);

            // ✅ Birthday
            JsonNode birthdayNode = rootNode.path("birthdays");
            String birthday = birthdayNode.isArray() && birthdayNode.size() > 0
                    ? birthdayNode.get(0).path("date").toString()
                    : "Not Available";
            extraDetails.put("birthday", birthday);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Google People API Response: " + response.getBody());
        return extraDetails;
    }
}
