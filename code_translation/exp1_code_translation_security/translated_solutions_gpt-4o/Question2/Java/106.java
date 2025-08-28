import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.ECPointUtil;
import org.bouncycastle.util.encoders.Hex;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

public class ECDSASignatureExample {

    public static void main(String[] args) {
        try {
            // Add BouncyCastle as a Security Provider
            Security.addProvider(new BouncyCastleProvider());

            // Generate ECDSA key pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyGen.initialize(ecSpec, new SecureRandom());
            KeyPair keyPair = keyGen.generateKeyPair();

            // Sign the message
            byte[] message = "helloworld".getBytes();
            byte[] signedMessage = sign(message, keyPair.getPrivate());

            // Verify the signature
            boolean isVerified = verify(message, signedMessage, keyPair.getPublic());
            System.out.println(isVerified);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Signs a given message using a given ECDSA signing key
     *
     * @param message input message
     * @param privateKey ECDSA private key used to sign the message
     * @return signed message
     */
    public static byte[] sign(byte[] message, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(message);
        return ecdsaSign.sign();
    }

    /**
     * Verifies a signed message using a given ECDSA public key
     *
     * @param message input message
     * @param signedMessage signed message
     * @param publicKey ECDSA public key used to verify the message
     * @return true if the signature is valid, false otherwise
     */
    public static boolean verify(byte[] message, byte[] signedMessage, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(message);
        return ecdsaVerify.verify(signedMessage);
    }
}