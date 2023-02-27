package in.projectzhu.freakyapi.service;

import in.projectzhu.freakyapi.domain.Profile;
import in.projectzhu.freakyapi.exceptions.EntityNotFoundException;
import in.projectzhu.freakyapi.repo.ProfileRepository;
import in.projectzhu.freakyapi.service.dto.ProfileDTO;
import in.projectzhu.freakyapi.service.mapper.ProfileMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@Log4j2
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileService(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;

        this.profileMapper = profileMapper;
  }

    public ProfileDTO findOneByGlobalUserId(Long globalUserId) throws EntityNotFoundException {
        log.info("Request to find profile by global user id : {}", globalUserId);

        Optional<Profile> optionalProfile = profileRepository.findOneByGlobalUserId(globalUserId);

        if (optionalProfile.isEmpty()) {
            throw new EntityNotFoundException("Profile with the specified global user id does not exist");
        }

        Profile profile = optionalProfile.get();

        return profileMapper.toDTO(profile);
    }

    public Profile findOneEntityByGlobalUserId(Long globalUserId) throws EntityNotFoundException {
        log.info("Request to find profile by global user id : {}", globalUserId);

        Optional<Profile> optionalProfile = profileRepository.findOneByGlobalUserId(globalUserId);

        if (optionalProfile.isEmpty()) {
            throw new EntityNotFoundException("Profile with the specified global user id does not exist");
        }

        return optionalProfile.get();
    }

    public Profile save(Profile profile) {
        log.info("Request to save profile : {}", profile);

        return profileRepository.save(profile);
    }
}
