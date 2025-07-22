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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
