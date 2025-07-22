package br.com.enlace.user.domain.http;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class MeetingScheduleDTO {

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private MeetingFrequency frequency;
    private Boolean isRecurring;
    private String timezone;

}
