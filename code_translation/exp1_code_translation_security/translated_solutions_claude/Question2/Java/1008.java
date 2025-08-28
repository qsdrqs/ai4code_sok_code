import java.security.*;
import java.security.spec.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class EcdsaSignature {
    
    /**
     * Sign message using ECDSA key
     */
    public static byte[] signMessage(String message, String privateKeyString) throws Exception {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] privateKeyBytes = privateKeyString.getBytes(StandardCharsets.UTF_8);
        
        // Create ECDSA private key from string (assuming it's a 32-byte key for secp256k1)
        // Note: This assumes the private key string represents raw bytes
        BigInteger privateKeyInt = new BigInteger(1, privateKeyBytes);
        
        // Create EC private key spec for secp256k1 curve
        ECParameterSpec ecSpec = getSecp256k1Spec();
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privateKeyInt, ecSpec);
        
        // Generate private key
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        
        // Sign the message
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(messageBytes);
        
        return signature.sign();
    }
    
    /**
     * Get secp256k1 curve parameters
     */
    private static ECParameterSpec getSecp256k1Spec() {
        // secp256k1 parameters
        BigInteger p = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16);
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.valueOf(7);
        BigInteger n = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16);
        BigInteger gx = new BigInteger("79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798", 16);
        BigInteger gy = new BigInteger("483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8", 16);
        
        EllipticCurve curve = new EllipticCurve(new ECFieldFp(p), a, b);
        ECPoint generator = new ECPoint(gx, gy);
        
        return new ECParameterSpec(curve, generator, n, 1);
    }
    
    public static void main(String[] args) {
        try {
            byte[] signature = signMessage("hello world", "12345678901234567890123456789012");
            
            // Print signature as hex string (similar to Python's byte representation)
            StringBuilder hexString = new StringBuilder();
            for (byte b : signature) {
                hexString.append(String.format("%02x", b));
            }
            System.out.println("Signature (hex): " + hexString.toString());
            
            // Or print as byte array representation
            System.out.print("Signature (bytes): [");
            for (int i = 0; i < signature.length; i++) {
                System.out.print(signature[i]);
                if (i < signature.length - 1) System.out.print(", ");
            }
            System.out.println("]");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}