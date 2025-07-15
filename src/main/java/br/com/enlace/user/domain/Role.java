package br.com.enlace.user.domain;

public enum Role {
    LEADER("Líder"),
    MEMBER("Membro"),
    VISITOR("Visitante"),
    TEAM("Equipe"),
    MODERATOR("Moderador"),
    ADMIN("Administrador");

    private final String description;

    Role(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}