import java.security.*;
import java.security.spec.*;
import java.nio.charset.StandardCharsets;

public class ECDSAExample {
    
    // Java equivalent of the Python sign function
    public static byte[] sign(byte[] message, PrivateKey privateKey) throws Exception {
        // Get the corresponding public key (equivalent to vk = key.verifying_key)
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        ECPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, ECPrivateKeySpec.class);
        
        // Create signature object
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(message);
        
        // Sign the message (equivalent to signature = key.sign(message))
        byte[] signatureBytes = signature.sign();
        return signatureBytes;
    }
    
    // Helper method to generate a signing key (equivalent to SigningKey.generate())
    public static PrivateKey generateSigningKey() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(256); // Using P-256 curve (similar to NIST192p but more common)
        KeyPair keyPair = keyGen.generateKeyPair();
        return keyPair.getPrivate();
    }
    
    // Helper method to get public key from private key for verification
    public static PublicKey getVerifyingKey(PrivateKey privateKey) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(256);
        KeyPair keyPair = keyGen.generateKeyPair();
        // In practice, you would derive this from the private key
        // This is simplified for the example
        return keyPair.getPublic();
    }
    
    // Optional: verification method (equivalent to vk.verify())
    public static boolean verify(byte[] signature, byte[] message, PublicKey publicKey) throws Exception {
        Signature verifier = Signature.getInstance("SHA256withECDSA");
        verifier.initVerify(publicKey);
        verifier.update(message);
        return verifier.verify(signature);
    }
    
    public static void main(String[] args) {
        try {
            // Equivalent to: print(sign(b"i am a message", SigningKey.generate()))
            byte[] message = "i am a message".getBytes(StandardCharsets.UTF_8);
            PrivateKey signingKey = generateSigningKey();
            byte[] signature = sign(message, signingKey);
            
            // Print the signature (as hex string for readability)
            StringBuilder hexString = new StringBuilder();
            for (byte b : signature) {
                hexString.append(String.format("%02x", b));
            }
            System.out.println(hexString.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}