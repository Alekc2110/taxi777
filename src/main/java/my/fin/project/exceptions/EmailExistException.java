package my.fin.project.exceptions;

public class EmailExistException extends RuntimeException {
    public EmailExistException() {
    }

    public EmailExistException(String message) {
        super(message);
    }
}
