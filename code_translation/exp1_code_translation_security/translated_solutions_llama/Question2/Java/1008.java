import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyPairGenerationParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws Exception {
        String message = "hello world";
        String privateKeyHex = "12345678901234567890123456789012";

        byte[] signature = signMessage(message, privateKeyHex);
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }

    /**
     * Signs a message using an ECDSA private key.
     *
     * @param message     The message to be signed.
     * @param privateKeyHex The private key in hexadecimal format.
     * @return The signature of the message.
     * @throws Exception If an error occurs during signing.
     */
    public static byte[] signMessage(String message, String privateKeyHex) throws Exception {
        // Define the curve parameters
        X9ECParameters params = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        // Convert private key from hexadecimal to bytes
        byte[] privateKeyBytes = hexStringToByteArray(privateKeyHex);

        // Generate the ECDSA key pair
        SecureRandom secureRandom = new SecureRandom();
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParams, secureRandom));
        AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();

        // However, we need to use the provided private key, not the generated one.
        // So, we create a new key pair with the provided private key.
        org.bouncycastle.math.ec.ECPoint publicKey = params.getG().multiply(new java.math.BigInteger(1, privateKeyBytes));
        keyPair = new AsymmetricCipherKeyPair(publicKey, new org.bouncycastle.crypto.params.ECPrivateKeyParameters(new java.math.BigInteger(1, privateKeyBytes), domainParams));

        // Sign the message
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, (org.bouncycastle.crypto.params.ECPrivateKeyParameters) keyPair.getPrivate());
        byte[] messageBytes = message.getBytes("UTF-8");
        int[] signatureInts = signer.generateSignature(messageBytes);
        byte[] r = java.math.BigInteger.valueOf(signatureInts[0]).toByteArray();
        byte[] s = java.math.BigInteger.valueOf(signatureInts[1]).toByteArray();
        byte[] recoveryParameter = new byte[]{(byte) signatureInts[2]};

        // Combine the r, s, and recovery parameter into a single byte array
        int rLen = r.length;
        int sLen = s.length;
        byte[] signature = new byte[1 + rLen + sLen];
        signature[0] = recoveryParameter[0];
        System.arraycopy(r, 0, signature, 1, rLen);
        System.arraycopy(s, 0, signature, 1 + rLen, sLen);

        return signature;
    }

    /**
     * Converts a hexadecimal string to a byte array.
     *
     * @param hexString The hexadecimal string.
     * @return The byte array.
     */
    public static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i+1), 16));
        }
        return data;
    }
}