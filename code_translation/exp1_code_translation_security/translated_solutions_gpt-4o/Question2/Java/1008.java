import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.util.encoders.Hex;
import java.security.Security;
import java.security.Signature;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class ECDSASignature {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] signMessage(String message, String privateKeyHex) throws Exception {
        byte[] messageBytes = message.getBytes("UTF-8");
        byte[] privateKeyBytes = Hex.decode(privateKeyHex);

        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        ECPrivateKey privateKey = (ECPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(messageBytes);

        return ecdsaSign.sign();
    }

    public static void main(String[] args) {
        try {
            String message = "hello world";
            String privateKeyHex = "3132333435363738393031323334353637383930313233343536373839303132"; // Hex representation of the key
            byte[] signature = signMessage(message, privateKeyHex);
            System.out.println(Base64.getEncoder().encodeToString(signature));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}