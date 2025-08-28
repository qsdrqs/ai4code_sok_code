import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.SecureRandom;

public class MyClass {
    public static byte[] myFunction(byte[] message) throws Exception {
        // Add BouncyCastle as a security provider
        Security.addProvider(new BouncyCastleProvider());

        // Generate the EC key pair using SECP256k1 curve
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
        keyPairGenerator.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Extract the private key
        ECPrivateKeyParameters privateKeyParameters = new ECPrivateKeyParameters(
                ((org.bouncycastle.jce.interfaces.ECPrivateKey) keyPair.getPrivate()).getD(),
                ecSpec
        );

        // Sign the message
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, new ParametersWithRandom(privateKeyParameters, new SecureRandom()));
        BigInteger[] signature = signer.generateSignature(message);

        // Convert the signature to a byte array (r and s concatenated)
        byte[] r = signature[0].toByteArray();
        byte[] s = signature[1].toByteArray();
        byte[] sig = new byte[r.length + s.length];
        System.arraycopy(r, 0, sig, 0, r.length);
        System.arraycopy(s, 0, sig, r.length, s.length);

        return sig;
    }
}