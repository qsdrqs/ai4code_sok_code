import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.math.ec.ECPoint;

import java.security.Security;
import java.math.BigInteger;

public class ECDSASignatureExample {

    static {
        // Add Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String signMessage(String message, byte[] privateKeyBytes) {
        // Hash the message
        SHA256Digest digest = new SHA256Digest();
        byte[] messageBytes = message.getBytes();
        digest.update(messageBytes, 0, messageBytes.length);
        byte[] messageHash = new byte[digest.getDigestSize()];
        digest.doFinal(messageHash, 0);

        // Sign the message
        ECDomainParameters ecParams = new ECDomainParameters(
                SECNamedCurves.getByName("secp256k1").getCurve(),
                SECNamedCurves.getByName("secp256k1").getG(),
                SECNamedCurves.getByName("secp256k1").getN(),
                SECNamedCurves.getByName("secp256k1").getH());

        ECPrivateKeyParameters privateKey = new ECPrivateKeyParameters(new BigInteger(1, privateKeyBytes), ecParams);
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privateKey);
        BigInteger[] signatureComponents = signer.generateSignature(messageHash);

        // Encode the signature in DER format
        byte[] derSignature = org.bouncycastle.asn1.sec.ECDSASignature.encodeToDER(signatureComponents[0], signatureComponents[1]);

        // Return the signature as a hex string
        return Hex.toHexString(derSignature);
    }

    public static void main(String[] args) {
        // Test the function
        byte[] privateKey = Hex.decode("e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109");
        String message = "Hello";
        String signature = signMessage(message, privateKey);
        System.out.println(signature);
    }
}