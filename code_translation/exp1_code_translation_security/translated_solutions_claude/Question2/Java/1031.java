import java.security.PrivateKey;
import java.security.Signature;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.SignatureException;

/**
 * Sign a message
 */
public static byte[] sign(byte[] msg, PrivateKey privKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
    // Create signature instance with SHA256withRSA (equivalent to PKCS1_v1_5 with SHA256)
    Signature signer = Signature.getInstance("SHA256withRSA");
    
    // Initialize with private key
    signer.initSign(privKey);
    
    // Update with message data
    signer.update(msg);
    
    // Generate and return signature
    byte[] signature = signer.sign();
    return signature;
}