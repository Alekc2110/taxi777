package my.fin.project.utils;
import org.apache.commons.codec.digest.DigestUtils;

public class SimplePasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(final String pass) {
        return DigestUtils.sha256Hex(pass);
    }
}
