import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Security;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.Base64;

public class EcdsaSign {

    static {
        // Register Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Signs a message using ECDSA with the secp192r1 curve and SHA-1 hash.
     * The signature is returned in DER-encoded ASN.1 format.
     */
    public static byte[] sign(String message, PrivateKey key) throws Exception {
        // Convert Java PrivateKey to Bouncy Castle's ECPrivateKeyParameters
        ECPrivateKeyParameters keyParams = (ECPrivateKeyParameters) PrivateKeyFactory.createKey(key.getEncoded());

        // Compute SHA-1 hash of the message
        SHA1Digest digest = new SHA1Digest();
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        digest.update(messageBytes, 0, messageBytes.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);

        // Sign using deterministic ECDSA (RFC 6979)
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, keyParams); // true = signing mode
        BigInteger[] sig = signer.generateSignature(hash);

        // Encode the signature in DER format (r, s)
        ASN1EncodableVector vec = new ASN1EncodableVector();
        vec.add(new ASN1Integer(sig[0]));
        vec.add(new ASN1Integer(sig[1]));
        return new DERSequence(vec).getEncoded();
    }

    public static void main(String[] args) throws Exception {
        // Generate a key pair using secp192r1 (NIST P-192)
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA", BouncyCastleProvider.PROVIDER_NAME);
        kpg.initialize(192); // secp192r1
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        // Sign the message
        byte[] signature = sign("something", privateKey);

        // Print the signature in Base64 for readability
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }
}