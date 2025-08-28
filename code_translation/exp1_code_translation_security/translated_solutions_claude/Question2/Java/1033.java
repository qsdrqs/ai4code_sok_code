import java.security.*;
import java.security.spec.*;
import java.security.interfaces.*;

public class ECDSAMessageSigner {
    
    public static byte[] signMessage(byte[] message, String curveName) throws Exception {
        // Generate a random private key for the specified curve
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec(curveName);
        keyGen.initialize(ecSpec);
        KeyPair keyPair = keyGen.generateKeyPair();
        
        // Get the private key for signing
        PrivateKey privateKey = keyPair.getPrivate();
        
        // Get the public key (equivalent to verifying_key in Python)
        PublicKey publicKey = keyPair.getPublic();
        
        // Create signature instance and sign the message
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(message);
        byte[] signatureBytes = signature.sign();
        
        return signatureBytes;
    }
    
    // Alternative method that accepts a PrivateKey directly
    public static byte[] signMessage(byte[] message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }
    
    // Example usage
    public static void main(String[] args) {
        try {
            String message = "Hello, World!";
            byte[] messageBytes = message.getBytes("UTF-8");
            
            // Sign with secp256r1 curve (equivalent to NIST P-256)
            byte[] signature = signMessage(messageBytes, "secp256r1");
            
            System.out.println("Message signed successfully!");
            System.out.println("Signature length: " + signature.length + " bytes");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}