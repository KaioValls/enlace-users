package br.com.enlace.user.validations.http.exceptions;

public class GroupDoesNotExistException extends RuntimeException {
    public GroupDoesNotExistException() {
        super("Grupo informado não existe");
    }
}
