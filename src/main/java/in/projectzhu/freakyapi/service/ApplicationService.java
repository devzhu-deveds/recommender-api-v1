package in.projectzhu.freakyapi.service;


import in.projectzhu.freakyapi.domain.Application;
import in.projectzhu.freakyapi.domain.enumerations.ApplicationStatus;
import in.projectzhu.freakyapi.exceptions.EntityNotFoundException;
import in.projectzhu.freakyapi.repo.ApplicationRepository;
import in.projectzhu.freakyapi.service.dto.ApplicationDTO;
import in.projectzhu.freakyapi.service.mapper.ApplicationMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Log4j2
@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final ApplicationMapper applicationMapper;

    public ApplicationService(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    /**
     * Create an application using the supplied data
     * Generate an api key and send back with response
     */
    public ApplicationDTO save(Application application) {
        log.debug("Request to save an application");

        application.setStatus(ApplicationStatus.ACTIVE);
        application.setCreatedOn(LocalDate.now());
        application.setApiKey("provisional");

        log.debug("Finished setting up fields, now saving...");

        application = applicationRepository.save(application);

        log.debug("Saved the application. Id is : {}", application.getId());

        log.debug("Creating an api key");
        String toBeEncoded = application.getId() + ":" + application.getDeveloperEmail() + ":" + application.getName()
                + ":" + System.currentTimeMillis();

        String apiKey = encode(toBeEncoded);
        log.debug("Created api key, now saving application details");

        application.setApiKey(apiKey);

        application = applicationRepository.save(application);

        ApplicationDTO applicationDTO = applicationMapper.toDTO(application);

        return applicationDTO;
    }

    /**
     * Return all the applications
     *
     * @return
     */
    public List<ApplicationDTO> getAll() {
        log.debug("Request to get all applications");

        List<Application> applications = applicationRepository.findAll();

        List<ApplicationDTO> applicationDTOList = applicationMapper.toDTO(applications);

        return applicationDTOList;
    }

    /**
     * Find an application by id
     *
     * @param id
     * @return
     * @throws EntityNotFoundException
     */
    public ApplicationDTO getOne(Long id) throws EntityNotFoundException {
        log.debug("Request to find application with id : {}", id);

        Optional<Application> optionalApplication = applicationRepository.findById(id);

        Application application = null;
        if (((Optional<?>) optionalApplication).isPresent()) {
            application = optionalApplication.get();
        } else {
            throw new EntityNotFoundException("Application with the specified id does not exist");
        }

        ApplicationDTO applicationDTO = applicationMapper.toDTO(application);

        return applicationDTO;
    }

    /**
     * Delete an application by id
     *
     * @param id
     */
    public void deleteOne(Long id) {
        log.debug("Request to delete application with id : {}", id);

        applicationRepository.deleteById(id);
    }

    /**
     * Update an application with the supplied application object
     *
     * @param application
     * @return
     */
    public ApplicationDTO update(Application application) {
        log.debug("Request to update application with id : {}", application.getId());

        application.setUpdatedOn(LocalDate.now());
        application = applicationRepository.save(application);

        ApplicationDTO applicationDTO = applicationMapper.toDTO(application);

        return applicationDTO;
    }

    /**
     * Encode a string using Base64
     *
     * @param toBeEncoded
     * @return
     */
    public String encode(String toBeEncoded) {
        byte[] bytes = toBeEncoded.getBytes(StandardCharsets.UTF_8);

        bytes = Base64.getEncoder().encode(bytes);

        String encodedString = new String(bytes);

        return encodedString;
    }
}
