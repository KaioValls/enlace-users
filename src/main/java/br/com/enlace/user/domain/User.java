package br.com.enlace.user.domain;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "nick_name", length = 100)
    private String nickName;

    private String description;

    @Column(length = 150)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "profile_photo_url")
    private String profilePhotoUrl;

    private UserStatus status = UserStatus.ACTIVE;

    @Column(name = "last_presence")
    private LocalDateTime lastPresence;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preferences_id")
    private UserPreferences preferences;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserGroupRoles> userGroupsRoles = new HashSet<>();
    //private Set<Authentication> authentications = new HashSet;

    public User(){}

    public User(Long id, String firstName, String lastName, String nickName, String description, String email, String phone, LocalDate birthDate, String profilePhotoUrl, UserStatus status, LocalDateTime lastPresence, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.description = description;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.profilePhotoUrl = profilePhotoUrl;
        this.status = status;
        this.lastPresence = lastPresence;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

    public void addUserGroupsRoles(UserGroupRoles userGroupsRoles){
        this.userGroupsRoles.add(userGroupsRoles);
    }

    public Set<UserGroupRoles> getUserGroupsRoles() {
        return userGroupsRoles;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public UserStatus getStatus() {
        return status;
    }

    public LocalDateTime getLastPresence() {
        return lastPresence;
    }

    public Address getAddress() {
        return address;
    }

    public UserPreferences getPreferences() {
        return preferences;
    }
}
