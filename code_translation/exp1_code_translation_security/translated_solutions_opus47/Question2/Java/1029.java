import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

public class EcdsaExample {

    public static byte[] ecdsaKey(byte[] message, Object key) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1"); // NIST P-384
        keyGen.initialize(ecSpec);
        KeyPair kp = keyGen.generateKeyPair();
        PrivateKey sk = kp.getPrivate();
        PublicKey vk = kp.getPublic();

        Signature sig = Signature.getInstance("SHA1withECDSA");
        sig.initSign(sk);
        sig.update(message);
        byte[] signature = sig.sign();

        sig.initVerify(vk);
        sig.update(message);
        System.out.println(sig.verify(signature));

        return signature;
    }

    public static boolean ecdsaVerify(byte[] signature, byte[] message, PublicKey vk) throws Exception {
        Signature sig = Signature.getInstance("SHA1withECDSA");
        sig.initVerify(vk);
        sig.update(message);
        return sig.verify(signature);
    }

    public static KeyPair ecdsaGenkey() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1"); // NIST P-384
        keyGen.initialize(ecSpec);
        return keyGen.generateKeyPair();
    }

    public static byte[] ecdsaSign(PrivateKey sk, byte[] message) throws Exception {
        Signature sig = Signature.getInstance("SHA1withECDSA");
        sig.initSign(sk);
        sig.update(message);
        return sig.sign();
    }

    public static boolean ecdsaVerifyKey(PublicKey vk, byte[] signature, byte[] message) throws Exception {
        Signature sig = Signature.getInstance("SHA1withECDSA");
        sig.initVerify(vk);
        sig.update(message);
        return sig.verify(signature);
    }

    public static void main(String[] args) throws Exception {
        byte[] message = "This is a test message".getBytes(StandardCharsets.UTF_8);
        KeyPair keyPair = ecdsaGenkey();
        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();

        System.out.println(Arrays.toString(sk.getEncoded()));
        System.out.println(Arrays.toString(vk.getEncoded()));

        byte[] signature = ecdsaSign(sk, message);
        System.out.println(Arrays.toString(signature));
        System.out.println(ecdsaVerifyKey(vk, signature, message));
    }
}