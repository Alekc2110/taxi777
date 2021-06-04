package my.fin.project.exceptions;

public class PhoneNumExistException extends RuntimeException {
    public PhoneNumExistException() {
    }

    public PhoneNumExistException(String message) {
        super(message);
    }
}
