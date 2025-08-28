import java.security.PrivateKey;
import java.security.Signature;

public byte[] signUsingECDSA(PrivateKey key, byte[] message) throws Exception {
    // Create a Signature instance for ECDSA with SHA-256
    Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
    
    // Initialize the Signature with the private key
    ecdsaSign.initSign(key);
    
    // Update the Signature with the message
    ecdsaSign.update(message);
    
    // Sign the message and return the signature
    return ecdsaSign.sign();
}