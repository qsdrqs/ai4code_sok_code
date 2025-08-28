import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyPairGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyPairParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECCustomNamedCurves;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;

import java.security.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // Add Bouncy Castle provider
        Security.addProvider(new BouncyCastleProvider());

        // Generate Keys
        AsymmetricCipherKeyPair keyPair = generateKeyPair();

        // Get public and private keys
        ECPoint publicKey = keyPair.getPublic();
        java.math.BigInteger privateKey = keyPair.getPrivate();

        String message = "My test message";

        // Generate Signature
        byte[] signatureBytes = generateSignature(message.getBytes(), privateKey, publicKey);

        // Verify if signature is valid
        System.out.println(verifySignature(message.getBytes(), signatureBytes, publicKey));
    }

    /**
     * Generate a key pair using the secp256r1 curve.
     *
     * @return The generated key pair.
     * @throws Exception If an error occurs during key generation.
     */
    private static AsymmetricCipherKeyPair generateKeyPair() throws Exception {
        X9ECParameters params = SECNamedCurves.getByName("secp256r1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParams, new SecureRandom()));
        return generator.generateKeyPair();
    }

    /**
     * Generate a signature for the given message using the provided private key.
     *
     * @param message    The message to sign.
     * @param privateKey The private key to use for signing.
     * @param publicKey  The public key corresponding to the private key.
     * @return The generated signature as a byte array.
     * @throws Exception If an error occurs during signature generation.
     */
    private static byte[] generateSignature(byte[] message, java.math.BigInteger privateKey, ECPoint publicKey) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, new ECKeyPairParameters(privateKey, publicKey));

        java.math.BigInteger[] signature = signer.generateSignature(message);
        byte[] r = signature[0].toByteArray();
        byte[] s = signature[1].toByteArray();
        byte[] recoveryParam = new byte[]{signer.getRecoveryParam()};

        // Convert to DER encoding
        int rLength = r.length;
        int sLength = s.length;
        int len = 1 + 1 + rLength + 1 + 1 + sLength + 1;
        byte[] der = new byte[len];
        int offset = 0;
        der[offset++] = 0x30; // SEQUENCE
        der[offset++] = (byte) (len - 2); // length
        der[offset++] = 0x02; // INTEGER
        der[offset++] = (byte) rLength; // length of r
        System.arraycopy(r, 0, der, offset, rLength);
        offset += rLength;
        der[offset++] = 0x02; // INTEGER
        der[offset++] = (byte) sLength; // length of s
        System.arraycopy(s, 0, der, offset, sLength);
        offset += sLength;
        der[offset++] = (byte) 0x03; // ECDSA-SIG-ATTR-1
        der[offset] = 1; // length
        der[offset + 1] = recoveryParam[0]; // recovery param

        return der;
    }

    /**
     * Verify the given signature for the provided message using the public key.
     *
     * @param message    The original message.
     * @param signature  The signature to verify.
     * @param publicKey  The public key to use for verification.
     * @return True if the signature is valid, false otherwise.
     * @throws Exception If an error occurs during verification.
     */
    private static boolean verifySignature(byte[] message, byte[] signature, ECPoint publicKey) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(false, new ECKeyPairParameters(null, publicKey));

        // Parse DER encoding
        int offset = 0;
        if (signature[offset++] != 0x30) {
            throw new Exception("Invalid DER encoding");
        }
        int len = signature[offset++];
        if (len != signature.length - 2) {
            throw new Exception("Invalid DER encoding");
        }
        if (signature[offset++] != 0x02) {
            throw new Exception("Invalid DER encoding");
        }
        int rLength = signature[offset++];
        byte[] r = new byte[rLength];
        System.arraycopy(signature, offset, r, 0, rLength);
        offset += rLength;
        if (signature[offset++] != 0x02) {
            throw new Exception("Invalid DER encoding");
        }
        int sLength = signature[offset++];
        byte[] s = new byte[sLength];
        System.arraycopy(signature, offset, s, 0, sLength);
        offset += sLength;

        // ECDSA-SIG-ATTR-1
        if (signature[offset++] != 0x03 || signature[offset++] != 1) {
            throw new Exception("Invalid DER encoding");
        }
        int recoveryParam = signature[offset];

        java.math.BigInteger[] values = new java.math.BigInteger[]{new java.math.BigInteger(1, r), new java.math.BigInteger(1, s)};
        return signer.verifySignature(message, values, recoveryParam);
    }
}