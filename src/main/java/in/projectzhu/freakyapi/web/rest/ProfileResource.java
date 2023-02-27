package in.projectzhu.freakyapi.web.rest;


import in.projectzhu.freakyapi.exceptions.EntityNotFoundException;
import in.projectzhu.freakyapi.service.ProfileService;
import in.projectzhu.freakyapi.service.dto.ProfileDTO;
import in.projectzhu.freakyapi.web.vm.ErrorVM;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Log4j2
@RequestMapping("/api")
@RestController
public class ProfileResource {

    private final ProfileService profileService;

    public ProfileResource(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        log.info("REST request to get profile for user : {}", id);

        ProfileDTO profileDTO = null;
        try {
            profileDTO = profileService.findOneByGlobalUserId(id);
        } catch (EntityNotFoundException e) {
            ErrorVM errorVM = new ErrorVM(404, e.getMessage());
            return ResponseEntity.status(404).body(errorVM);
        }

        return ResponseEntity.ok().body(profileDTO);
    }
}
