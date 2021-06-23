package my.fin.project.exceptions;

public class EntitySaveDaoException extends RuntimeException {
    public EntitySaveDaoException() {
    }

    public EntitySaveDaoException(String message) {
        super(message);
    }
}
