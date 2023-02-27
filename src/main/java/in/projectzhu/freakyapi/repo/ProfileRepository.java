package in.projectzhu.freakyapi.repo;

import in.projectzhu.freakyapi.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findOneByGlobalUserId(Long globalUserId);
}
