package com.LavaCandy.LearningOAuth2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeControler {

    @GetMapping
    public String home() {
        org.springframework.security.oauth2.core.oidc.user.OidcUser oidcUser =
            (org.springframework.security.oauth2.core.oidc.user.OidcUser)
            org.springframework.security.core.context.SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();

        String username = oidcUser.getFullName();
        String email = oidcUser.getEmail();

        return "Welcome, " + username + "! Your email is: " + email;
    }

    @GetMapping("/home")
    public String homeS(@AuthenticationPrincipal OAuth2User userPrincipal) {
        return "Hello!! " + userPrincipal.getAttribute("name") +
               " Your email is: " + userPrincipal.getAttribute("email");
    }
    
}
