package br.com.enlace.user.domain.http;

import java.time.LocalDateTime;

public class MemberActivityDTO {

    private Long userId;
    private Integer meetingsAttended;
    private Integer meetingsTotal;
    private Double attendanceRate;
    private LocalDateTime lastActivity;
    private Integer contributions;
    private EngagementLevel engagementLevel;

}
