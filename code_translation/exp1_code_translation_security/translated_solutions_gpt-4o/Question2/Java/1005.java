import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.util.encoders.Hex;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class ECDSASignature {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] sign(byte[] msg, PrivateKey privateKey) throws Exception {
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(msg);
        byte[] signature = ecdsaSign.sign();

        // Verify the signature
        PublicKey publicKey = KeyFactory.getInstance("ECDSA", "BC").generatePublic(((ECPrivateKey) privateKey).getParams());
        Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(msg);
        if (!ecdsaVerify.verify(signature)) {
            throw new SignatureException("Signature verification failed");
        }

        return signature;
    }

    public static void test() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyGen.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();

        byte[] signature = sign("message".getBytes(), keyPair.getPrivate());
        System.out.println(Hex.toHexString(signature));
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}