package br.com.enlace.user.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "user_preferences")
public class UserPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notification_email")
    private Boolean notificationEmail = true;

    @Column(name = "notification_sms")
    private Boolean notificationSms = false;

    @Column(name = "notification_push")
    private Boolean notificationPush = true;

    @Column(name = "privacy_profile")
    private Boolean privacyProfile = false;

    @Column(name = "privacy_contact")
    private Boolean privacyContact = false;

    private String language;
    private String timezone;

    @Column(name = "dark_mode")
    private Boolean darkMode = false;


}
