import java.security.*;
import java.security.spec.*;
import java.security.interfaces.ECPrivateKey;

public class ECDSASignature {
    
    public static byte[] signString(ECPrivateKey privateKey, byte[] data) throws Exception {
        // Create a Signature instance for ECDSA with SHA256
        Signature signature = Signature.getInstance("SHA256withECDSA");
        
        // Initialize the signature with the private key
        signature.initSign(privateKey);
        
        // Update the signature with the data to be signed
        signature.update(data);
        
        // Generate and return the signature
        return signature.sign();
    }
    
    // Helper method to generate a private key (equivalent to ec.generate_private_key(ec.SECP384R1()))
    public static ECPrivateKey generatePrivateKey() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1");
        keyGen.initialize(ecSpec);
        KeyPair keyPair = keyGen.generateKeyPair();
        return (ECPrivateKey) keyPair.getPrivate();
    }
    
    // Example usage (equivalent to the commented print statement)
    public static void main(String[] args) {
        try {
            ECPrivateKey privateKey = generatePrivateKey();
            byte[] data = "hello world".getBytes();
            byte[] signature = signString(privateKey, data);
            
            // Print signature length (Java signatures are in DER format by default)
            System.out.println("Signature length: " + signature.length);
            System.out.println("Signature generated successfully");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}