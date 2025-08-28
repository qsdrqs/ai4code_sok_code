import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveGenParameterSpec;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class ECDSAExample {
    
    static {
        // Add Bouncy Castle provider
        Security.addProvider(new BouncyCastleProvider());
    }
    
    public static byte[] sign(byte[] msg, PrivateKey privateKey) throws Exception {
        // Get the corresponding public key for verification
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        PublicKey publicKey = getPublicKeyFromPrivate(privateKey);
        
        // Sign the message
        Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
        signature.initSign(privateKey);
        signature.update(msg);
        byte[] signatureBytes = signature.sign();
        
        // Verify the signature (equivalent to assert vk.verify(signature, msg))
        Signature verifier = Signature.getInstance("SHA256withECDSA", "BC");
        verifier.initVerify(publicKey);
        verifier.update(msg);
        boolean isValid = verifier.verify(signatureBytes);
        
        if (!isValid) {
            throw new RuntimeException("Signature verification failed");
        }
        
        return signatureBytes;
    }
    
    private static PublicKey getPublicKeyFromPrivate(PrivateKey privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        org.bouncycastle.jce.interfaces.ECPrivateKey ecPrivateKey = 
            (org.bouncycastle.jce.interfaces.ECPrivateKey) privateKey;
        
        org.bouncycastle.math.ec.ECPoint publicPoint = 
            ecPrivateKey.getParameters().getG().multiply(ecPrivateKey.getD());
        
        org.bouncycastle.jce.spec.ECPublicKeySpec publicKeySpec = 
            new org.bouncycastle.jce.spec.ECPublicKeySpec(publicPoint, ecPrivateKey.getParameters());
        
        return keyFactory.generatePublic(publicKeySpec);
    }
    
    public static void test() throws Exception {
        // Generate a new ECDSA key pair (equivalent to ecdsa.SigningKey.generate())
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1"); // P-256 curve
        keyGen.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();
        
        PrivateKey privateKey = keyPair.getPrivate();
        
        // Sign the message "message"
        byte[] signature = sign("message".getBytes(), privateKey);
        
        // Print the signature (as hex string for readability)
        System.out.println(bytesToHex(signature));
    }
    
    // Helper method to convert bytes to hex string for display
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
    
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}