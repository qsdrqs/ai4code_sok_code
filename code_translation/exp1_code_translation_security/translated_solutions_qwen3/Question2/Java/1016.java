import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class ECDSASigner {

    static {
        // Register Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Signs a message using an ECDSA private key with the SECP256k1 curve.
     *
     * @param message       The message to be signed (raw bytes).
     * @param privateKey    The private key as a 32-byte array (big-endian scalar).
     * @return              The signature as a 64-byte array (r || s, each 32 bytes).
     */
    public static byte[] sign(byte[] message, byte[] privateKey) {
        try {
            // Step 1: Hash the message using SHA-1 (as in Python's ecdsa)
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(message);

            // Step 2: Convert the private key bytes to a BigInteger
            BigInteger d = new BigInteger(1, privateKey);

            // Step 3: Get the SECP256k1 curve parameters
            ECNamedCurveParameterSpec curveSpec = ECNamedCurveTable.getParameterSpec("secp256k1");

            // Step 4: Create the private key parameters
            ECPrivateKeyParameters keyParams = new ECPrivateKeyParameters(d, curveSpec);

            // Step 5: Initialize the ECDSA signer
            ECDSASigner signer = new ECDSASigner();
            signer.init(true, new ParametersWithRandom(keyParams));

            // Step 6: Update the signer with the hash and generate the signature
            signer.update(hash, 0, hash.length);
            BigInteger[] rs = signer.generateSignature();

            // Step 7: Encode r and s as 32-byte big-endian arrays
            byte[] rBytes = rs[0].toByteArray();
            byte[] sBytes = rs[1].toByteArray();

            byte[] rBytes32 = new byte[32];
            byte[] sBytes32 = new byte[32];

            // Right-justify (pad with leading zeros if necessary)
            System.arraycopy(rBytes, 0, rBytes32, 32 - rBytes.length, rBytes.length);
            System.arraycopy(sBytes, 0, sBytes32, 32 - sBytes.length, sBytes.length);

            // Step 8: Concatenate r and s into a 64-byte signature
            byte[] signature = new byte[64];
            System.arraycopy(rBytes32, 0, signature, 0, 32);
            System.arraycopy(sBytes32, 0, signature, 32, 32);

            return signature;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-1 algorithm not found", e);
        } catch (Exception e) {
            throw new RuntimeException("Error generating ECDSA signature", e);
        }
    }
}