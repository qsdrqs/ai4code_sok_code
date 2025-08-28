import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class ECDSADemo {

    /**
     * Sign the supplied data with the given EC private key
     *
     * @param key  EC private key
     * @param data data to be signed
     * @return DER-encoded ECDSA signature
     */
    public static byte[] sign(PrivateKey key, byte[] data) throws Exception {
        Signature signer = Signature.getInstance("SHA256withECDSA");
        signer.initSign(key);
        signer.update(data);
        return signer.sign();
    }

    public static void test() throws Exception {
        // Generate an EC key pair on the secp384r1 (aka NIST P-384) curve
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(new ECGenParameterSpec("secp384r1"), new SecureRandom());
        KeyPair keyPair = kpg.generateKeyPair();

        byte[] data = "this is some data I'd like to sign".getBytes(StandardCharsets.UTF_8);
        byte[] signature = sign(keyPair.getPrivate(), data);

        // For display purposes
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}