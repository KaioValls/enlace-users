package br.com.enlace.user.domain.http;

public enum GroupType {
    CELL("Célula"),
    TEAM("Equipe"),
    MINISTRY("Ministério"),
    DEPARTMENT("Departamento"),
    COMMITTEE("Comitê"),
    PRAYER_GROUP("Grupo de Oração"),
    GENERIC("Genérico");

    private final String description;

    GroupType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
