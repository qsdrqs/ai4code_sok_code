import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyPairGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws Exception {
        // Generate Keys
        AsymmetricCipherKeyPair keyPair = generateKeyPair();
        ECPrivateKeyParameters privateKey = (ECPrivateKeyParameters) keyPair.getPrivate();
        String message = "My test message";
        String signedMessage = generateEcdsaSignedMsg(message, privateKey);
        System.out.println(signedMessage);
    }

    /**
     * Generate a key pair using the secp256r1 curve.
     *
     * @return The generated key pair.
     * @throws Exception If an error occurs during key generation.
     */
    private static AsymmetricCipherKeyPair generateKeyPair() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        X9ECParameters params = SECNamedCurves.getByName("secp256r1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParams, secureRandom));
        return generator.generateKeyPair();
    }

    /**
     * Generate an ECDSA signed message.
     *
     * @param message    The message to be signed.
     * @param privateKey The private key used for signing.
     * @return The signed message in Base64 format.
     * @throws Exception If an error occurs during signing.
     */
    public static String generateEcdsaSignedMsg(String message, ECPrivateKeyParameters privateKey) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privateKey);

        byte[] messageBytes = message.getBytes();
        int[] r = signer.generateSignature(messageBytes);
        byte[] signatureBytes = new byte[64];
        System.arraycopy(bigIntToBytes(r[0]), 0, signatureBytes, 0, 32);
        System.arraycopy(bigIntToBytes(r[1]), 0, signatureBytes, 32, 32);

        return Base64.getEncoder().encodeToString(signatureBytes);
    }

    /**
     * Convert a BigInteger to a byte array.
     *
     * @param value The BigInteger value.
     * @return The byte array representation.
     */
    private static byte[] bigIntToBytes(BigInteger value) {
        byte[] bytes = new byte[32];
        byte[] valBytes = value.toByteArray();
        System.arraycopy(valBytes, 0, bytes, bytes.length - valBytes.length, valBytes.length);
        return bytes;
    }
}