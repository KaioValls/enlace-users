package br.com.enlace.user.domain.http;


import java.time.LocalDateTime;
import java.util.List;

public class GroupDTO {

    private Long id;
    private String name;
    private String description;
    private GroupType type;
    private Long leaderID;
    private List<Long> membersIdList;
    private InteractionRulesDTO interactionRules;
    private GroupLocationDTO location;
    private TreasuryDTO treasury;
    // TODO: NOSQL : private GroupStatisticsDTO statistics;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private GroupStatus status;
    private MeetingScheduleDTO meetingSchedule;
    private Long boxId;


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public GroupType getType() {
        return type;
    }

    public Long getLeaderID() {
        return leaderID;
    }

    public List<Long> getMembersIdList() {
        return membersIdList;
    }

    public InteractionRulesDTO getInteractionRules() {
        return interactionRules;
    }

    public Long getBoxId() {
        return boxId;
    }

    public Long getId() {
        return id;
    }

    public GroupStatus getStatus() {
        return status;
    }
}
