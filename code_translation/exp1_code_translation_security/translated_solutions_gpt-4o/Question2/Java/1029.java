import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.util.encoders.Hex;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;

public class ECDSAExample {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static KeyPair ecdsaGenKey() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("P-384");
        keyGen.initialize(ecSpec, new SecureRandom());
        return keyGen.generateKeyPair();
    }

    public static byte[] ecdsaSign(PrivateKey sk, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature ecdsaSign = Signature.getInstance("SHA384withECDSA", "BC");
        ecdsaSign.initSign(sk);
        ecdsaSign.update(message);
        return ecdsaSign.sign();
    }

    public static boolean ecdsaVerifyKey(PublicKey vk, byte[] signature, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature ecdsaVerify = Signature.getInstance("SHA384withECDSA", "BC");
        ecdsaVerify.initVerify(vk);
        ecdsaVerify.update(message);
        return ecdsaVerify.verify(signature);
    }

    public static void main(String[] args) {
        try {
            byte[] message = "This is a test message".getBytes();

            KeyPair keyPair = ecdsaGenKey();
            PrivateKey sk = keyPair.getPrivate();
            PublicKey vk = keyPair.getPublic();

            System.out.println(Hex.toHexString(sk.getEncoded()));
            System.out.println(Hex.toHexString(vk.getEncoded()));

            byte[] signature = ecdsaSign(sk, message);
            System.out.println(Hex.toHexString(signature));

            boolean isVerified = ecdsaVerifyKey(vk, signature, message);
            System.out.println(isVerified);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}