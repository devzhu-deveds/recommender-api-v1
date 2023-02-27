package in.projectzhu.freakyapi.domain;


import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity(name = "tbl_events")
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long applicationId;

    private String eventType;

    private String eventValue;

    private LocalDateTime createdOn;

    private Long userId;

    private Long globalUserId;

    private Boolean isProcessed;

    //no args constructor
    public Event() {
    }

    //all args constructor
    public Event(Long id, Long applicationId, String eventType, String eventValue, LocalDateTime createdOn, Long userId, Long globalUserId, Boolean isProcessed) {
        this.id = id;
        this.applicationId = applicationId;
        this.eventType = eventType;
        this.eventValue = eventValue;
        this.createdOn = createdOn;
        this.userId = userId;
        this.globalUserId = globalUserId;
        this.isProcessed = isProcessed;
    }


    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventValue() {
        return eventValue;
    }

    public void setEventValue(String eventValue) {
        this.eventValue = eventValue;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGlobalUserId() {
        return globalUserId;
    }

    public void setGlobalUserId(Long globalUserId) {
        this.globalUserId = globalUserId;
    }

    public Boolean getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(Boolean processed) {
        isProcessed = processed;
    }


//to string

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", applicationId=" + applicationId +
                ", eventType='" + eventType + '\'' +
                ", eventValue='" + eventValue + '\'' +
                ", createdOn=" + createdOn +
                ", userId=" + userId +
                ", globalUserId=" + globalUserId +
                ", isProcessed=" + isProcessed +
                '}';
    }

}
