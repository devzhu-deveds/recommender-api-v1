package in.projectzhu.freakyapi.repo;


import in.projectzhu.freakyapi.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
