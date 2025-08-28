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
        // Generate a key pair
        AsymmetricCipherKeyPair keyPair = generateKeyPair();

        // Message to be signed
        byte[] message = "hehehe".getBytes();

        // Sign the message
        byte[] sig = sign(keyPair.getPrivate(), message);

        // Verify the signature
        boolean isValid = verify(keyPair.getPublic(), sig, message);
        System.out.println(isValid);
    }

    /**
     * Generate a key pair using the NIST384p curve.
     *
     * @return AsymmetricCipherKeyPair
     * @throws Exception
     */
    private static AsymmetricCipherKeyPair generateKeyPair() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        X9ECParameters params = SECNamedCurves.getByName("secp384r1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParams, secureRandom));
        return generator.generateKeyPair();
    }

    /**
     * Sign a message using the provided private key.
     *
     * @param privateKey Private key
     * @param message    Message to be signed
     * @return Signature
     * @throws Exception
     */
    private static byte[] sign(org.bouncycastle.crypto.params.ECPrivateKeyParameters privateKey, byte[] message) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privateKey);
        BigInteger[] signature = signer.generateSignature(message);
        // Convert signature to DER format
        return derEncode(signature[0], signature[1], privateKey.getParameters().getN());
    }

    /**
     * Verify a signature using the provided public key.
     *
     * @param publicKey Public key
     * @param sig       Signature
     * @param message   Message
     * @return True if the signature is valid, false otherwise
     * @throws Exception
     */
    private static boolean verify(org.bouncycastle.crypto.params.ECPublicKeyParameters publicKey, byte[] sig, byte[] message) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(false, publicKey);
        return signer.verifySignature(message, derDecode(sig));
    }

    // Helper methods for DER encoding and decoding

    private static byte[] derEncode(BigInteger r, BigInteger s, BigInteger n) {
        int size = 2 + (r.bitLength() + 7) / 8 + (s.bitLength() + 7) / 8;
        byte[] der = new byte[size];
        int offset = 0;
        der[offset++] = 0x30; // SEQUENCE
        der[offset++] = (byte) (size - 2);
        der[offset++] = 0x02; // INTEGER
        int rSize = (r.bitLength() + 7) / 8;
        der[offset++] = (byte) rSize;
        for (int i = rSize - 1; i >= 0; i--) {
            der[offset++] = r.toByteArray()[i];
        }
        der[offset++] = 0x02; // INTEGER
        int sSize = (s.bitLength() + 7) / 8;
        der[offset++] = (byte) sSize;
        for (int i = sSize - 1; i >= 0; i--) {
            der[offset++] = s.toByteArray()[i];
        }
        return der;
    }

    private static BigInteger[] derDecode(byte[] der) {
        int offset = 0;
        if (der[offset++] != 0x30) {
            throw new RuntimeException("Invalid DER encoding");
        }
        int len = der[offset++] & 0xff;
        if (len > der.length - offset) {
            throw new RuntimeException("Invalid DER encoding");
        }
        offset += len;
        BigInteger r = derDecodeInt(der, offset);
        offset += (r.bitLength() + 7) / 8 + 2;
        BigInteger s = derDecodeInt(der, offset);
        return new BigInteger[]{r, s};
    }

    private static BigInteger derDecodeInt(byte[] der, int offset) {
        if (der[offset++] != 0x02) {
            throw new RuntimeException("Invalid DER encoding");
        }
        int len = der[offset++] & 0xff;
        byte[] bytes = new byte[len];
        System.arraycopy(der, offset, bytes, 0, len);
        return new BigInteger(1, bytes);
    }
}