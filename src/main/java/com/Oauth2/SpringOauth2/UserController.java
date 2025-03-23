package com.Oauth2.SpringOauth2;

import com.Oauth2.SpringOauth2.GooglePeopleApiService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/")
    public String Hello(){
        return "Hello";
    }

    private final GooglePeopleApiService googlePeopleApiService;

    public UserController(GooglePeopleApiService googlePeopleApiService) {
        this.googlePeopleApiService = googlePeopleApiService;
    }

    @GetMapping("/user-details")
    public String userDetails(@AuthenticationPrincipal OAuth2User oauthUser, Model model,
                              @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
        if (oauthUser != null) {
            // ✅ Get Access Token
            String accessToken = authorizedClient.getAccessToken().getTokenValue();
            System.out.println("Access Token: " + accessToken); // Debugging

            // ✅ Fetch Extra Details
            Map<String, Object> extraDetails = googlePeopleApiService.fetchExtraDetails(accessToken);

            // ✅ Pass Details to Model
            model.addAttribute("name", extraDetails.get("name"));
            model.addAttribute("email", extraDetails.get("email"));
            model.addAttribute("picture", extraDetails.get("picture"));
            model.addAttribute("gender", extraDetails.get("gender"));
            model.addAttribute("address", extraDetails.get("address"));
            model.addAttribute("phoneNo", extraDetails.get("phoneNumber"));
            model.addAttribute("birthday", extraDetails.get("birthday"));

            System.out.println("User Details: " + extraDetails); // Debugging
        }

        return "user-details";
    }
}
