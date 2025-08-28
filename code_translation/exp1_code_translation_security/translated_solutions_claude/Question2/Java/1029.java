import java.security.*;
import java.security.spec.*;
import java.math.BigInteger;

public class ECDSAUtils {
    
    private static final String CURVE_NAME = "secp384r1"; // NIST P-384
    private static final String ALGORITHM = "SHA256withECDSA";
    
    public static class KeyPair {
        public final PrivateKey privateKey;
        public final PublicKey publicKey;
        
        public KeyPair(PrivateKey privateKey, PublicKey publicKey) {
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        }
    }
    
    public static byte[] ecdsaKey(byte[] message, String key) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec(CURVE_NAME);
            keyGen.initialize(ecSpec);
            
            java.security.KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey sk = keyPair.getPrivate();
            PublicKey vk = keyPair.getPublic();
            
            Signature signature = Signature.getInstance(ALGORITHM);
            signature.initSign(sk);
            signature.update(message);
            byte[] signatureBytes = signature.sign();
            
            // Verify the signature
            signature.initVerify(vk);
            signature.update(message);
            boolean verified = signature.verify(signatureBytes);
            System.out.println(verified);
            
            return signatureBytes;
        } catch (Exception e) {
            throw new RuntimeException("ECDSA signing failed", e);
        }
    }
    
    public static boolean ecdsaVerify(byte[] signatureBytes, byte[] message, PublicKey vk) {
        try {
            Signature signature = Signature.getInstance(ALGORITHM);
            signature.initVerify(vk);
            signature.update(message);
            return signature.verify(signatureBytes);
        } catch (Exception e) {
            throw new RuntimeException("ECDSA verification failed", e);
        }
    }
    
    public static KeyPair ecdsaGenkey() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec(CURVE_NAME);
            keyGen.initialize(ecSpec);
            
            java.security.KeyPair keyPair = keyGen.generateKeyPair();
            return new KeyPair(keyPair.getPrivate(), keyPair.getPublic());
        } catch (Exception e) {
            throw new RuntimeException("Key generation failed", e);
        }
    }
    
    public static byte[] ecdsaSign(PrivateKey sk, byte[] message) {
        try {
            Signature signature = Signature.getInstance(ALGORITHM);
            signature.initSign(sk);
            signature.update(message);
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException("ECDSA signing failed", e);
        }
    }
    
    public static boolean ecdsaVerifyKey(PublicKey vk, byte[] signatureBytes, byte[] message) {
        try {
            Signature signature = Signature.getInstance(ALGORITHM);
            signature.initVerify(vk);
            signature.update(message);
            return signature.verify(signatureBytes);
        } catch (Exception e) {
            throw new RuntimeException("ECDSA verification failed", e);
        }
    }
    
    // Helper method to convert byte array to hex string for printing
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
    
    // Helper method to get key bytes for printing (similar to Python's to_string())
    private static String keyToString(Key key) {
        return bytesToHex(key.getEncoded());
    }
    
    public static void main(String[] args) {
        try {
            byte[] message = "This is a test message".getBytes();
            
            KeyPair keyPair = ecdsaGenkey();
            PrivateKey sk = keyPair.privateKey;
            PublicKey vk = keyPair.publicKey;
            
            System.out.println(keyToString(sk));
            System.out.println(keyToString(vk));
            
            byte[] signature = ecdsaSign(sk, message);
            System.out.println(bytesToHex(signature));
            
            boolean verified = ecdsaVerifyKey(vk, signature, message);
            System.out.println(verified);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}