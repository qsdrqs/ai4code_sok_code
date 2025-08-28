import java.security.*;
import java.security.spec.*;
import java.security.interfaces.ECPrivateKey;
import java.math.BigInteger;
import java.util.Base64;
import javax.xml.bind.DatatypeConverter;

/**
 * Sign message ECDSA
 */
public class ECDSAMessageSigner {
    
    /**
     * Sign message using ECDSA with SECP256k1 curve
     * 
     * @param message The message to sign as byte array
     * @param privateKey The private key as byte array
     * @return The signature as byte array
     * @throws Exception if signing fails
     */
    public static byte[] signMessage(byte[] message, byte[] privateKey) throws Exception {
        // Create SECP256k1 curve parameters
        ECParameterSpec secp256k1 = createSECP256k1Params();
        
        // Convert private key bytes to BigInteger
        BigInteger privateKeyInt = new BigInteger(1, privateKey);
        
        // Create ECPrivateKeySpec
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privateKeyInt, secp256k1);
        
        // Generate private key object
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privKey = keyFactory.generatePrivate(privateKeySpec);
        
        // Create signature instance
        Signature signature = Signature.getInstance("SHA1withECDSA");
        signature.initSign(privKey);
        signature.update(message);
        
        // Sign and return
        return signature.sign();
    }
    
    /**
     * Create SECP256k1 curve parameters
     */
    private static ECParameterSpec createSECP256k1Params() {
        // SECP256k1 parameters
        BigInteger p = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16);
        BigInteger a = BigInteger.ZERO;
        BigInteger b = new BigInteger("7", 16);
        BigInteger n = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16);
        BigInteger gx = new BigInteger("79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798", 16);
        BigInteger gy = new BigInteger("483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8", 16);
        
        EllipticCurve curve = new EllipticCurve(
            new ECFieldFp(p),
            a, b
        );
        
        ECPoint generator = new ECPoint(gx, gy);
        
        return new ECParameterSpec(curve, generator, n, 1);
    }
    
    /**
     * Convenience method for signing with hex string private key
     */
    public static byte[] signMessage(byte[] message, String privateKeyHex) throws Exception {
        byte[] privateKeyBytes = DatatypeConverter.parseHexBinary(privateKeyHex);
        return signMessage(message, privateKeyBytes);
    }
    
    /**
     * Convenience method for signing string message
     */
    public static byte[] signMessage(String message, byte[] privateKey) throws Exception {
        return signMessage(message.getBytes("UTF-8"), privateKey);
    }
    
    /**
     * Convenience method for signing string message with hex private key
     */
    public static byte[] signMessage(String message, String privateKeyHex) throws Exception {
        byte[] privateKeyBytes = DatatypeConverter.parseHexBinary(privateKeyHex);
        return signMessage(message.getBytes("UTF-8"), privateKeyBytes);
    }
}