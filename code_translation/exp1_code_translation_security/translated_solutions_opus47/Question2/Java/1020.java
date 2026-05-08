import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class ECDSAExample {
    // Register BouncyCastle provider (needed for secp256k1 support)
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    // 'vk' is captured from outer scope (like in the Python code)
    private static PublicKey vk;

    public static void main(String[] args) throws Exception {
        // sk = ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyGen.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();

        PrivateKey sk = keyPair.getPrivate();
        // vk = sk.get_verifying_key()
        vk = keyPair.getPublic();

        // print(sign_message(sk, ".."))
        System.out.println(signMessage(sk, "..".getBytes()));
    }

    public static boolean signMessage(PrivateKey sk, byte[] message) throws Exception {
        // sig = sk.sign(message)  -- Python's ecdsa default hash is SHA-1
        Signature signer = Signature.getInstance("SHA1withECDSA", "BC");
        signer.initSign(sk);
        signer.update(message);
        byte[] sig = signer.sign();

        // return vk.verify(sig, message)
        Signature verifier = Signature.getInstance("SHA1withECDSA", "BC");
        verifier.initVerify(vk);
        verifier.update(message);
        return verifier.verify(sig);
    }
}