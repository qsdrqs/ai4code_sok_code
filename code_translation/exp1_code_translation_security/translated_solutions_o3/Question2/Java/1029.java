import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Direct translation of the original Python sample that uses
 *   - NIST P-384 (a.k.a. “secp384r1”) EC curve
 *   - plain ECDSA sign / verify
 *
 * All utility methods keep (almost) the same names and behaviour as the
 * Python version.
 *
 * NOTE:  This code expects the Bouncy-Castle provider to be available at
 * runtime.  The build/dependency management (Maven/Gradle/…​) is assumed
 * to be taken care of outside this snippet, as stated in the task.
 */
public final class ECDSAExample {

    /*  ---------- 1.  Python-equivalent helper methods ---------- */

    // Generates a key, signs the supplied message and instantly verifies it.
    // Prints the verify result and returns the signature bytes.
    public static byte[] ecdsa_key(byte[] message) throws Exception {
        KeyPair keyPair = ecdsa_genkey();
        byte[] signature = ecdsa_sign(keyPair.getPrivate(), message);

        // Print verification result (true / false) just like the Python code.
        System.out.println(ecdsa_verify_key(keyPair.getPublic(), signature, message));

        return signature;
    }

    // Plain verify helper (same as python ecdsa_verify)
    public static boolean ecdsa_verify(byte[] signature,
                                       byte[] message,
                                       PublicKey vk) throws Exception {
        return ecdsa_verify_key(vk, signature, message);
    }

    // Generate a fresh EC (P-384) key pair
    public static KeyPair ecdsa_genkey() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1"); // NIST-P384
        kpg.initialize(ecSpec, new SecureRandom());
        return kpg.generateKeyPair();
    }

    // Sign the message with the provided private key
    public static byte[] ecdsa_sign(PrivateKey sk, byte[] message) throws Exception {
        Signature sig = Signature.getInstance("SHA384withECDSA", "BC");
        sig.initSign(sk, new SecureRandom());
        sig.update(message);
        return sig.sign();
    }

    // Verify (public key, signature, message) exactly like the python helper
    public static boolean ecdsa_verify_key(PublicKey vk,
                                           byte[] signature,
                                           byte[] message) throws Exception {
        Signature sig = Signature.getInstance("SHA384withECDSA", "BC");
        sig.initVerify(vk);
        sig.update(message);
        return sig.verify(signature);
    }


    /*  ---------- 2.  Simple driver (mirrors the python __main__) ---------- */

    public static void main(String[] args) throws Exception {

        // Bouncy-Castle must be registered once; easiest is to do it right here
        Security.addProvider(new BouncyCastleProvider());

        byte[] message = "This is a test message".getBytes("UTF-8");

        KeyPair kp = ecdsa_genkey();
        PrivateKey sk = kp.getPrivate();
        PublicKey  vk = kp.getPublic();

        // Print raw key data (DER encodings) so the output is similar to python
        System.out.println(Arrays.toString(sk.getEncoded()));
        System.out.println(Arrays.toString(vk.getEncoded()));

        // Sign, print the signature bytes, then verify and print the result
        byte[] signature = ecdsa_sign(sk, message);
        System.out.println(Arrays.toString(signature));
        System.out.println(ecdsa_verify_key(vk, signature, message));
    }

    // Utility class → no instances
    private ECDSAExample() {}
}