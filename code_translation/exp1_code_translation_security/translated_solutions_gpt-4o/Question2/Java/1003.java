import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.util.encoders.Hex;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

public class ECDSASignature {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] signNist256(byte[] message, byte[] signingKeyRaw) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256r1");
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        ECPrivateKey privateKey = (ECPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(signingKeyRaw));

        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(message);

        return ecdsaSign.sign();
    }

    public static boolean verifyNist256(byte[] message, byte[] signature, byte[] verifyingKeyRaw) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256r1");
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        ECPublicKey publicKey = (ECPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(verifyingKeyRaw));

        Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(message);

        return ecdsaVerify.verify(signature);
    }

    public static void main(String[] args) {
        // Example usage
        try {
            byte[] message = "Hello, World!".getBytes();
            byte[] signingKeyRaw = Hex.decode("your_private_key_in_hex");
            byte[] verifyingKeyRaw = Hex.decode("your_public_key_in_hex");

            byte[] signature = signNist256(message, signingKeyRaw);
            boolean isVerified = verifyNist256(message, signature, verifyingKeyRaw);

            System.out.println("Signature: " + Hex.toHexString(signature));
            System.out.println("Verification: " + isVerified);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}