/*
 *  Function : signMessage
 *  Input    : message (byte[]), raw ECDSA private key (byte[])
 *  Output   : the message signed with the supplied key (byte[])
 *
 *  NOTE: The code relies on the Bouncy-Castle provider for the SECP-256k1 curve.
 *        Add the corresponding JAR to your class-path (bcprov-*.jar) – the
 *        build system used in your project will take care of downloading it.
 */

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.util.Arrays;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SignMessage {

    /**
     * Signs a message with the supplied raw SECP-256k1 private key.
     *
     * @param message        The message to sign.
     * @param privateKeyRaw  32 bytes representing the private key.
     * @return               DER-encoded ECDSA signature.
     */
    public static byte[] signMessage(byte[] message, byte[] privateKeyRaw) throws Exception {
        // Make sure the Bouncy Castle provider is available.
        Security.addProvider(new BouncyCastleProvider());

        // 1. Re-create the private key object from the raw bytes.
        BigInteger d = new BigInteger(1, privateKeyRaw);
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        PrivateKey privateKey =
                keyFactory.generatePrivate(new ECPrivateKeySpec(d, ecSpec));

        // 2. Sign the message (the Python library uses SHA-1 as default).
        Signature signer = Signature.getInstance("SHA1withECDSA", "BC");
        signer.initSign(privateKey);
        signer.update(message);

        return signer.sign();
    }

    /* Small demo replicating the Python example. */
    public static void main(String[] args) throws Exception {
        byte[] message     = "hello".getBytes(StandardCharsets.UTF_8);
        byte[] privateKey  = "qwertyuiopasdfghjklzxcvbnmqwerty"
                             .getBytes(StandardCharsets.UTF_8);

        byte[] signature = signMessage(message, privateKey);
        System.out.println(Arrays.toString(signature));
    }
}