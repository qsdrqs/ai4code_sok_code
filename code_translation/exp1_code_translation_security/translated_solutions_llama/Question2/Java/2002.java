import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyPairGenerationParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    static {
        // Add Bouncy Castle provider
        java.security.Security.addProvider(new BouncyCastleProvider());
    }

    public static class ECDSASignature {
        public byte[] r;
        public byte[] s;
        public byte[] recoveryId;

        public ECDSASignature(byte[] r, byte[] s, byte[] recoveryId) {
            this.r = r;
            this.s = s;
            this.recoveryId = recoveryId;
        }
    }

    public static ECDSASignature sign(AsymmetricCipherKeyPair keyPair, String message) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, keyPair.getPrivate());
        byte[] messageBytes = message.getBytes("UTF-8");
        BigInteger[] signature = signer.generateSignature(messageBytes);
        byte[] r = signature[0].toByteArray();
        byte[] s = signature[1].toByteArray();
        // recovery id is not directly available in ECDSASigner, 
        // but we can calculate it manually
        int recoveryId = signer.getRecoveryId();
        return new ECDSASignature(r, s, new byte[]{(byte) recoveryId});
    }

    public static boolean verify(AsymmetricCipherKeyPair keyPair, ECDSASignature signature, String message) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(false, keyPair.getPublic());
        byte[] messageBytes = message.getBytes("UTF-8");
        return signer.verifySignature(messageBytes, new BigInteger(1, signature.r), new BigInteger(1, signature.s));
    }

    public static void main(String[] args) throws Exception {
        // Generate key pair
        X9ECParameters params = SECNamedCurves.getByName("secp256r1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParams, new SecureRandom()));
        AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();

        // Sign message
        String message = "hello world";
        ECDSASignature signature = sign(keyPair, message);

        // Verify signature
        String wrongMessage = "hello worfld";
        boolean isValid = verify(keyPair, signature, wrongMessage);
        System.out.println(isValid);
    }
}