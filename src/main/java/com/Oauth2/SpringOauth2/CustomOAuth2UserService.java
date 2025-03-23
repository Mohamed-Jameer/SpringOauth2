package com.Oauth2.SpringOauth2;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.Oauth2.SpringOauth2.GooglePeopleApiService;
import com.Oauth2.SpringOauth2.GooglePeopleApiService;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final GooglePeopleApiService googlePeopleApiService;

    public CustomOAuth2UserService(GooglePeopleApiService googlePeopleApiService) {
        this.googlePeopleApiService = googlePeopleApiService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oauth2User.getAttributes();

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        String picture = (String) attributes.get("picture");

        // Fetch Extra Details Using People API
        String accessToken = userRequest.getAccessToken().getTokenValue();
        Map<String, Object> extraDetails = googlePeopleApiService.fetchExtraDetails(accessToken);

        String gender = (String) extraDetails.getOrDefault("gender", "Not Available");
        String address = (String) extraDetails.getOrDefault("address", "Not Available");
        String phoneNo = (String) extraDetails.getOrDefault("phoneNumber", "Not Available");

        // Debugging Information
        System.out.println("User: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNo);
        System.out.println("Gender: " + gender);
        System.out.println("Address: " + address);

        return oauth2User;
    }
}
