import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.LongFunction;

public class MD5 extends Method {
    MessageDigest md;
    byte[] array = new byte[8];

    public MD5() {
        try {
            md =  MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long apply(long value) {
        ByteBuffer.wrap(array).putLong(value);
        var blah = md.digest(array);
        return ByteBuffer.wrap(blah).getLong();
    }

    @Override
    public String toString() {
        return "MD5";
    }
}
