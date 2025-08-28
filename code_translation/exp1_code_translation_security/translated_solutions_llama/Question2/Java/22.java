import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyPairGenerationParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws Exception {
        String message = "Hello, World!";
        byte[] signature = myFunction(message.getBytes());
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }

    public static byte[] myFunction(byte[] message) throws Exception {
        // Generate a new ECDSA key pair
        X9ECParameters params = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParams, new SecureRandom()));
        AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();

        // Sign the message
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, keyPair.getPrivate());
        int[] signature = signer.generateSignature(message);

        // Convert the signature to a byte array
        byte[] r = org.bouncycastle.util.BigIntegers.asUnsignedByteArray(signature[0]);
        byte[] s = org.bouncycastle.util.BigIntegers.asUnsignedByteArray(signature[1]);
        int rLen = signature[0].bitLength() / 8 + 1;
        int sLen = signature[1].bitLength() / 8 + 1;
        byte[] derSignature = new byte[rLen + sLen + 1];
        derSignature[0] = 0x30;
        derSignature[1] = (byte) (rLen + sLen);
        System.arraycopy(r, r.length - rLen, derSignature, 2, rLen);
        derSignature[2 + rLen] = 0x02;
        derSignature[2 + rLen + 1] = (byte) sLen;
        System.arraycopy(s, s.length - sLen, derSignature, 2 + rLen + 2, sLen);

        return derSignature;
    }
}