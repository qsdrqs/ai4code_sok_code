import java.security.*;
import java.security.spec.*;
import java.security.interfaces.ECPrivateKey;
import java.math.BigInteger;

/**
 * A function in Java that signs a given message using a given ECDSA signing key.
 */
public class ECDSASigner {
    
    /**
     * Sign a message using a given ECDSA signing key.
     * 
     * @param message The message to be signed (as byte array)
     * @param privateKey The private key as byte array (32 bytes for SECP256k1)
     * @return The signed message as byte array
     * @throws Exception if signing fails
     */
    public static byte[] sign(byte[] message, byte[] privateKey) throws Exception {
        // Convert the private key bytes to BigInteger
        BigInteger privateKeyInt = new BigInteger(1, privateKey);
        
        // Create SECP256k1 curve parameters
        ECParameterSpec ecSpec = getSecp256k1Params();
        
        // Create the private key specification
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privateKeyInt, ecSpec);
        
        // Generate the private key object
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        ECPrivateKey ecPrivateKey = (ECPrivateKey) keyFactory.generatePrivate(privateKeySpec);
        
        // Create a signature object
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(ecPrivateKey);
        
        // Sign the message
        signature.update(message);
        byte[] signedMessage = signature.sign();
        
        return signedMessage;
    }
    
    /**
     * Get SECP256k1 curve parameters
     */
    private static ECParameterSpec getSecp256k1Params() {
        // SECP256k1 parameters
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
    
    // Example usage
    public static void main(String[] args) {
        try {
            // Example message and private key (32 bytes for SECP256k1)
            String messageStr = "Hello, World!";
            byte[] message = messageStr.getBytes("UTF-8");
            
            // Example private key (32 bytes) - replace with actual private key
            byte[] privateKey = new byte[32];
            // Fill with your actual private key bytes
            for (int i = 0; i < 32; i++) {
                privateKey[i] = (byte) (i + 1); // Just for example
            }
            
            // Sign the message
            byte[] signature = sign(message, privateKey);
            
            System.out.println("Message signed successfully!");
            System.out.println("Signature length: " + signature.length + " bytes");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}