import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Main {

    public static String signMessage(String message, byte[] privateKey) throws Exception {
        // Define the elliptic curve parameters
        X9ECParameters params = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        // Create a private key parameter
        ECPrivateKeyParameters privateKeyParam = new ECPrivateKeyParameters(new java.math.BigInteger(1, privateKey), domainParams);

        // Create an ECDSA signer
        ECDSASigner signer = new ECDSASigner();

        // Initialize the signer
        signer.init(true, privateKeyParam);

        // Hash the message
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = md.digest(message.getBytes("UTF-8"));

        // Sign the message
        byte[] signature = signer.generateSignature(messageHash);

        // Convert the signature to hexadecimal
        StringBuilder signatureHex = new StringBuilder();
        for (byte b : signature) {
            signatureHex.append(String.format("%02x", b));
        }

        return signatureHex.toString();
    }

    public static void main(String[] args) throws Exception {
        // Test the function
        String privateKeyHex = "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
        byte[] privateKey = java.util.HexFormat.decodeFromHex(privateKeyHex);
        String message = "Hello";
        String signature = signMessage(message, privateKey);
        System.out.println(signature);
    }
}