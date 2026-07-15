package com.pluralsight.controllers;

import com.pluralsight.models.Profile;
import com.pluralsight.models.User;
import com.pluralsight.models.authentication.LoginDto;
import com.pluralsight.models.authentication.LoginResponseDto;
import com.pluralsight.models.authentication.RegisterUserDto;
import com.pluralsight.security.jwt.JWTFilter;
import com.pluralsight.security.jwt.TokenProvider;
import com.pluralsight.service.ProfileService;
import com.pluralsight.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@PreAuthorize("permitAll()")
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private UserService userService;
    private ProfileService profileService;

    public AuthenticationController(TokenProvider tokenProvider,
                                    AuthenticationManager authenticationManager,
                                    UserService userService,
                                    ProfileService profileService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.profileService = profileService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, false);

            User user = userService.getUserName(loginDto.getUsername());

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return new ResponseEntity<>(new LoginResponseDto(jwt, user), httpHeaders, HttpStatus.OK);
        }
        catch (AuthenticationException e)
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> register(@Valid @RequestBody RegisterUserDto registerUserDto)
    {
        boolean exists = userService.exists(registerUserDto.getUsername());
        if (exists)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        User user = userService.create(new User(0L, registerUserDto.getUsername(),
                registerUserDto.getPassword(), registerUserDto.getRole()));

        Profile profile = new Profile();
        profile.setUserId(user.getId());
        profileService.create(profile);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
