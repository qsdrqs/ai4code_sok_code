import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecP192K1Curve;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;

public class Main {
    static {
        // Add Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        // Generate EC key pair using NIST192p curve
        ECKeyPairGenerator kpg = new ECKeyPairGenerator();
        ECDomainParameters params = new ECDomainParameters(
            new SecP192K1Curve(), // NIST192p curve
            SecP192K1Curve.G,     // Base point
            SecP192K1Curve.N      // Order of the base point
        );
        kpg.init(new ECKeyGenerationParameters(new SecureRandom(), params));
        org.bouncycastle.crypto.AsymmetricCipherKeyPair keyPair = kpg.generateKeyPair();
        ECPrivateKeyParameters sk = (ECPrivateKeyParameters) keyPair.getPrivate();

        // Sign the message
        byte[] signature = sign("Hello world", sk);

        // Print the signature in hexadecimal format
        System.out.println(Hex.toHexString(signature));
    }

    public static byte[] sign(String inp, ECPrivateKeyParameters key) {
        // Compute SHA-1 hash of the input
        SHA1Digest digest = new SHA1Digest();
        byte[] data = inp.getBytes(StandardCharsets.UTF_8);
        digest.update(data, 0, data.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);

        // Sign using ECDSASigner
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, key);
        BigInteger[] rs = signer.generateSignature(hash);

        // Get field size (in bytes) for the curve
        int fieldSize = (key.getParameters().getCurve().getFieldSize() + 7) / 8;

        // Convert r and s to byte arrays and pad to field size
        byte[] r = rs[0].toByteArray();
        byte[] s = rs[1].toByteArray();

        byte[] rPadded = new byte[fieldSize];
        byte[] sPadded = new byte[fieldSize];

        System.arraycopy(r, 0, rPadded, fieldSize - r.length, r.length);
        System.arraycopy(s, 0, sPadded, fieldSize - s.length, s.length);

        // Concatenate r and s
        byte[] signature = new byte[fieldSize * 2];
        System.arraycopy(rPadded, 0, signature, 0, fieldSize);
        System.arraycopy(sPadded, 0, signature, fieldSize, fieldSize);

        return signature;
    }
}