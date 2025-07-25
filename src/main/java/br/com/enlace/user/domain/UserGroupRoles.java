package br.com.enlace.user.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_group_roles")
public class UserGroupRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "group_id")
    private Long groupId;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "joined_at")
    private LocalDateTime joinedAt;
    @Column(name = "is_active")
    private Boolean isActive = true;

    @PrePersist
    protected void onCreate(){
        joinedAt = LocalDateTime.now();
    }

    public UserGroupRoles() {
    }

    public UserGroupRoles(Long id, User user, Long groupId, Role role, LocalDateTime joinedAt, Boolean isActive) {
        this.id = id;
        this.user = user;
        this.groupId = groupId;
        this.role = role;
        this.joinedAt = joinedAt;
        this.isActive = isActive;
    }

    public Long getGroupId() {
        return groupId;
    }

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
