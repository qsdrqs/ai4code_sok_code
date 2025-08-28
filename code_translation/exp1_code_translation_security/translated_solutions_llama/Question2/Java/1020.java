import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyPairGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyPairParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws Exception {
        // Generate a new key pair
        AsymmetricCipherKeyPair keyPair = generateKeyPair();

        // Sign a message
        String message = "..";
        byte[] signature = signMessage(keyPair.getPrivate(), message.getBytes());

        // Verify the signature
        boolean isValid = verifySignature(keyPair.getPublic(), message.getBytes(), signature);

        System.out.println(isValid);
    }

    /**
     * Generate a new ECDSA key pair using the SECP256k1 curve.
     *
     * @return The generated key pair.
     * @throws Exception If an error occurs during key generation.
     */
    private static AsymmetricCipherKeyPair generateKeyPair() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        X9ECParameters params = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyPairGenerationParameters(domainParams, secureRandom));
        return generator.generateKeyPair();
    }

    /**
     * Sign a message using the provided private key.
     *
     * @param privateKey The private key to use for signing.
     * @param message    The message to sign.
     * @return The signature of the message.
     * @throws Exception If an error occurs during signing.
     */
    private static byte[] signMessage(org.bouncycastle.crypto.params.ECPrivateKeyParameters privateKey, byte[] message) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privateKey);
        BigInteger[] signature = signer.generateSignature(message);
        return encodeSignature(signature);
    }

    /**
     * Verify a signature using the provided public key.
     *
     * @param publicKey  The public key to use for verification.
     * @param message    The message that was signed.
     * @param signature  The signature to verify.
     * @return True if the signature is valid, false otherwise.
     * @throws Exception If an error occurs during verification.
     */
    private static boolean verifySignature(org.bouncycastle.crypto.params.ECPublicKeyParameters publicKey, byte[] message, byte[] signature) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(false, publicKey);
        BigInteger[] decodedSignature = decodeSignature(signature);
        return signer.verifySignature(message, decodedSignature[0], decodedSignature[1]);
    }

    /**
     * Encode a signature into a byte array.
     *
     * @param signature The signature to encode.
     * @return The encoded signature.
     */
    private static byte[] encodeSignature(BigInteger[] signature) {
        byte[] r = signature[0].toByteArray();
        byte[] s = signature[1].toByteArray();

        // Ensure r and s are 32 bytes long (256 bits)
        byte[] encodedR = new byte[32];
        byte[] encodedS = new byte[32];
        System.arraycopy(r, Math.max(0, r.length - 32), encodedR, Math.max(0, 32 - r.length), Math.min(r.length, 32));
        System.arraycopy(s, Math.max(0, s.length - 32), encodedS, Math.max(0, 32 - s.length), Math.min(s.length, 32));

        byte[] signatureBytes = new byte[64];
        System.arraycopy(encodedR, 0, signatureBytes, 0, 32);
        System.arraycopy(encodedS, 0, signatureBytes, 32, 32);
        return signatureBytes;
    }

    /**
     * Decode a signature from a byte array.
     *
     * @param signature The signature to decode.
     * @return The decoded signature.
     */
    private static BigInteger[] decodeSignature(byte[] signature) {
        BigInteger r = new BigInteger(1, new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, signature[0], signature[1], signature[2], signature[3], signature[4], signature[5], signature[6], signature[7], signature[8], signature[9], signature[10], signature[11], signature[12], signature[13], signature[14], signature[15], signature[16], signature[17], signature[18], signature[19], signature[20], signature[21], signature[22], signature[23], signature[24], signature[25], signature[26], signature[27], signature[28], signature[29], signature[30], signature[31]});
        BigInteger s = new BigInteger(1, new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, signature[32], signature[33], signature[34], signature[35], signature[36], signature[37], signature[38], signature[39], signature[40], signature[41], signature[42], signature[43], signature[44], signature[45], signature[46], signature[47], signature[48], signature[49], signature[50], signature[51], signature[52], signature[53], signature[54], signature[55], signature[56], signature[57], signature[58], signature[59], signature[60], signature[61], signature[62], signature[63]});
        return new BigInteger[]{r, s};
    }
}