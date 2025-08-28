import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECPrivateKeySpec;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

public class CryptoUtils {

    /**
     * Signs the supplied message with the given secp256k1 private key.
     *
     * @param message     data to be signed
     * @param privateKey  32-byte raw secp256k1 private key (same format that
     *                    ecdsa.SigningKey.from_string(...) expects in Python)
     * @return            DER-encoded ECDSA signature
     *
     * @throws Exception  if anything goes wrong during signing
     */
    public static byte[] signMessage(byte[] message, byte[] privateKey) throws Exception {

        // Make sure the BC provider is registered.
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

        /* 1. Turn the raw 32-byte key into a Java PrivateKey instance. */
        ECNamedCurveParameterSpec params = ECNamedCurveTable.getParameterSpec("secp256k1");
        BigInteger d = new BigInteger(1, privateKey);       // private scalar
        ECPrivateKeySpec privKeySpec = new ECPrivateKeySpec(d, params);

        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        PrivateKey pk = keyFactory.generatePrivate(privKeySpec);

        /* 2. Create and initialise the signer.                       *
         *    The Python code signs the raw message, i.e. without an   *
         *    internal hash step, so we use the "NONEwithECDSA"        *
         *    algorithm to obtain the same behaviour.                  */
        Signature signer = Signature.getInstance("NONEwithECDSA", "BC");
        signer.initSign(pk);
        signer.update(message);

        /* 3. Produce and return the signature. */
        return signer.sign();
    }
}