package in.projectzhu.freakyapi.service.dto;

import in.projectzhu.freakyapi.domain.Product;

import java.time.LocalDateTime;
import java.util.List;

public class ProfileDTO {
    private Long id;

    private Long globalUserId;

    private List<Product> recommendations;

    private LocalDateTime updatedOn;

    public ProfileDTO(Long id, Long globalUserId, List<Product> recommendations, LocalDateTime updatedOn) {
        this.setId(id);
        this.setGlobalUserId(globalUserId);
        this.setRecommendations(recommendations);
        this.setUpdatedOn(updatedOn);
    }

    public ProfileDTO() {
    }

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

    public List<Product> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Product> recommendations) {
        this.recommendations = recommendations;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
