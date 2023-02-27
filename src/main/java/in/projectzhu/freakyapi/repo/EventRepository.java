package in.projectzhu.freakyapi.repo;

import in.projectzhu.freakyapi.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findAllByGlobalUserId(Long globalUserId, Pageable pageable);
    Page<Event> findAllByApplicationId(Long applicationId, Pageable pageable);
    List<Event> findAllByIdGreaterThan(Long id);
}
