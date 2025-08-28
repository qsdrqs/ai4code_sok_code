import java.security.*;
import java.security.spec.*;
import java.security.interfaces.ECPrivateKey;
import java.math.BigInteger;

public class MessageSigner {
    
    /**
     * Signs a given message using ECDSA signing key
     * @param message message to be signed
     * @param privateKey private key bytes to sign the message
     * @return signature of the message
     * @throws Exception if signing fails
     */
    public static byte[] signMessage(byte[] message, byte[] privateKey) throws Exception {
        // Create SECP256k1 curve parameters
        ECParameterSpec secp256k1Spec = createSECP256k1Spec();
        
        // Convert private key bytes to BigInteger
        BigInteger privateKeyInt = new BigInteger(1, privateKey);
        
        // Create ECPrivateKeySpec
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privateKeyInt, secp256k1Spec);
        
        // Generate private key object
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privKey = keyFactory.generatePrivate(privateKeySpec);
        
        // Create signature instance
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privKey);
        signature.update(message);
        
        // Sign and return
        return signature.sign();
    }
    
    /**
     * Creates SECP256k1 curve parameter specification
     * @return ECParameterSpec for SECP256k1 curve
     */
    private static ECParameterSpec createSECP256k1Spec() {
        // SECP256k1 parameters
        BigInteger p = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16);
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.valueOf(7);
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
}