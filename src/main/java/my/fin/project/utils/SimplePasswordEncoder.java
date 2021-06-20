package my.fin.project.utils;
import org.apache.commons.codec.digest.DigestUtils;


public class SimplePasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(final String pass) {
        return DigestUtils.sha256Hex(pass);
    }

//    @Override
//    public String decode(String passEncoded) {
//        MessageDigest instance = null;
//        try {
//            instance = MessageDigest.getInstance();
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return String.valueOf(DigestUtils.digest(instance, passEncoded.getBytes()));
//
//    }
}
