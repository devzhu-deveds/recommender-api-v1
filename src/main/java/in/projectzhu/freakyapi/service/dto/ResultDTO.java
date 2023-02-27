package in.projectzhu.freakyapi.service.dto;


import java.util.List;

public class ResultDTO {
    private Long eventId;

    private Long userId;

    private Long globalUserId;

    private List<Integer> recommended;

    public ResultDTO() {
    }

    public ResultDTO(Long eventId, Long userId, Long globalUserId, List<Integer> recommended) {
        this.setEventId(eventId);
        this.setUserId(userId);
        this.setGlobalUserId(globalUserId);
        this.setRecommended(recommended);
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
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

    public List<Integer> getRecommended() {
        return recommended;
    }

    public void setRecommended(List<Integer> recommended) {
        this.recommended = recommended;
    }
}
