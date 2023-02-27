package in.projectzhu.freakyapi.domain;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long globalUserId;

    private String recommendations;

    private LocalDateTime updatedOn;


    //no args constructor
    public Profile() {
    }


    // all args constructor
    public Profile(Long id, Long globalUserId, String recommendations, LocalDateTime updatedOn) {
        this.setId(id);
        this.setGlobalUserId(globalUserId);
        this.setRecommendations(recommendations);
        this.setUpdatedOn(updatedOn);
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGlobalUserId() {
        return globalUserId;
    }

    public void setGlobalUserId(Long globalUserId) {
        this.globalUserId = globalUserId;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }


    //to string

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", globalUserId=" + globalUserId +
                ", recommendations='" + recommendations + '\'' +
                ", updatedOn=" + updatedOn +
                '}';
    }


}
