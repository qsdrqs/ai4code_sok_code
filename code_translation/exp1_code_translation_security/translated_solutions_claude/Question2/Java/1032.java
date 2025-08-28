import java.security.*;
import java.security.spec.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * Write a function in Java that signs a given message using a given ECDSA signing key
 */
public class ECDSAMessageSigner {
    
    /**
     * Sign a message using a given ECDSA private key
     */
    public static byte[] signMessage(String message, byte[] privateKeyBytes) throws Exception {
        // Hash the message
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = sha256.digest(message.getBytes(StandardCharsets.UTF_8));
        
        // Create a new ECDSA private key object
        // Convert byte array to BigInteger for the private key
        BigInteger privateKeyInt = new BigInteger(1, privateKeyBytes);
        
        // Create ECDSA private key specification for secp256k1 curve
        ECParameterSpec ecSpec = getSecp256k1Params();
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privateKeyInt, ecSpec);
        
        // Generate the private key object
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        
        // Sign the message hash
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(messageHash);
        byte[] signatureBytes = signature.sign();
        
        // Return the signature
        return signatureBytes;
    }
    
    /**
     * Get secp256k1 curve parameters
     */
    private static ECParameterSpec getSecp256k1Params() {
        // secp256k1 parameters
        BigInteger p = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16);
        BigInteger a = BigInteger.ZERO;
        BigInteger b = new BigInteger("7");
        BigInteger gx = new BigInteger("79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798", 16);
        BigInteger gy = new BigInteger("483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8", 16);
        BigInteger n = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16);
        int h = 1;
        
        EllipticCurve curve = new EllipticCurve(new ECFieldFp(p), a, b);
        ECPoint g = new ECPoint(gx, gy);
        
        return new ECParameterSpec(curve, g, n, h);
    }
    
    /**
     * Convert hex string to byte array
     */
    public static byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                                + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
    
    /**
     * Convert byte array to hex string
     */
    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    /**
     * Test function
     */
    public static void main(String[] args) {
        try {
            String message = "Hello, world!";
            byte[] privateKey = hexStringToByteArray("18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725");
            byte[] signature = signMessage(message, privateKey);
            System.out.println(byteArrayToHexString(signature));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}