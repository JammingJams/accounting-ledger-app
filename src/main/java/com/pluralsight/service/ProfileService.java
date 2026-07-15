package com.pluralsight.service;

import com.pluralsight.models.Profile;
import com.pluralsight.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProfileService
{
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository)
    {
        this.profileRepository = profileRepository;
    }

    public Profile create(Profile profile)
    {
        return profileRepository.save(profile);
    }

    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

    public Profile update(Profile profile, Long userId) {
        Profile existing = profileRepository.findByUserId(userId);

        existing.setAddress(profile.getAddress());
        existing.setCity(profile.getCity());
        existing.setEmail(profile.getEmail());
        existing.setPhone(profile.getPhone());
        existing.setState(profile.getState());
        existing.setFirstName(profile.getFirstName());
        existing.setLastName(profile.getLastName());
        existing.setZip(profile.getZip());

        return profileRepository.save(existing);
    }
}
