package br.com.enlace.user.domain.http;

import java.time.LocalDateTime;
import java.util.Map;

public class GroupStatisticsDTO {

    private int totalMembers;
    private int activeMembers;
    private Double averageAttendance;
    private int meetingsThisMonth;
    private int totalMeetings;
    private double engagementScore;
    private double growthRate;
    private LocalDateTime lastMeeting;
    private LocalDateTime nextMeeting;
    private Map<String, Integer> attendanceByMonth;
    private Map<Long, MemberActivityDTO> memberActivity;


}
