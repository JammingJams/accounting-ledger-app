package com.pluralsight.controllers;

import com.pluralsight.models.Profile;
import com.pluralsight.models.User;
import com.pluralsight.service.ProfileService;
import com.pluralsight.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    public ProfileController(ProfileService profileService,
                             UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Profile> getProfile(Principal principal) {

        String userName = principal.getName();
        User user = userService.getUserName(userName);
        Long userId = user.getId();

        if (profileService.getProfileByUserId(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profileService.getProfileByUserId(userId));
    }

    @PutMapping
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile, Principal principal) {

        String userName = principal.getName();
        User user = userService.getUserName(userName);
        Long userId = user.getId();

        if (profileService.getProfileByUserId(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profileService.update(profile, userId));

    }
}
