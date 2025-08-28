import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class ECDSASignatureExample {

    public static byte[] sign(PrivateKey privateKey, byte[] data) throws Exception {
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(data);
        return ecdsaSign.sign();
    }

    public static void test() throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp384r1");
        keyPairGenerator.initialize(ecSpec, new SecureRandom());

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        byte[] data = "this is some data I'd like to sign".getBytes();
        byte[] signature = sign(privateKey, data);

        System.out.println(java.util.Base64.getEncoder().encodeToString(signature));
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}