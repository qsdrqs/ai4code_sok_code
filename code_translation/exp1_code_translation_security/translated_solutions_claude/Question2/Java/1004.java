import java.security.*;
import java.security.spec.*;
import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

public class ECDSASignature {
    
    /**
     * Function sign_message
     * 
     * Input: message and ECDSA key
     * Output: message signed using the provided key
     */
    public static byte[] sign_message(byte[] message, byte[] private_key) throws Exception {
        // Create ECDSA key specification for SECP256k1 curve
        ECParameterSpec ecSpec = getSecp256k1Spec();
        
        // Convert private key bytes to BigInteger (assuming it's 32 bytes for secp256k1)
        BigInteger privateKeyInt = new BigInteger(1, private_key);
        
        // Create private key spec
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privateKeyInt, ecSpec);
        
        // Generate the private key
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privKey = keyFactory.generatePrivate(privateKeySpec);
        
        // Create signature instance
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privKey);
        signature.update(message);
        
        // Sign the message
        byte[] signatureBytes = signature.sign();
        return signatureBytes;
    }
    
    // Helper method to get SECP256k1 curve parameters
    private static ECParameterSpec getSecp256k1Spec() {
        // SECP256k1 parameters
        BigInteger p = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16);
        BigInteger a = BigInteger.ZERO;
        BigInteger b = new BigInteger("7");
        BigInteger gx = new BigInteger("79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798", 16);
        BigInteger gy = new BigInteger("483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8", 16);
        BigInteger n = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16);
        int h = 1;
        
        EllipticCurve curve = new EllipticCurve(
            new ECFieldFp(p),
            a, b
        );
        
        ECPoint generator = new ECPoint(gx, gy);
        
        return new ECParameterSpec(curve, generator, n, h);
    }
    
    public static void main(String[] args) {
        try {
            byte[] message = "hello".getBytes("UTF-8");
            byte[] privateKey = "qwertyuiopasdfghjklzxcvbnmqwerty".getBytes("UTF-8");
            
            byte[] signature = sign_message(message, privateKey);
            
            // Print signature as hex string for readability
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