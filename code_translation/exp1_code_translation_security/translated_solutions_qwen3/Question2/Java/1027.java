import java.security.PrivateKey;
import java.security.Signature;

public class JavaSigningKey implements SigningKey {
    private final PrivateKey privateKey;
    private final String algorithm;

    public JavaSigningKey(PrivateKey privateKey, String algorithm) {
        this.privateKey = privateKey;
        this.algorithm = algorithm;
    }

    @Override
    public byte[] sign(byte[] message) throws Exception {
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }
}