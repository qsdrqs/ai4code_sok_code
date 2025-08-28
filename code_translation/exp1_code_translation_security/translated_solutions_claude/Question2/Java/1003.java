import java.security.*;
import java.security.spec.*;
import java.math.BigInteger;

public class ECDSAUtils {
    
    public static byte[] signNist256(byte[] message, byte[] signingKeyRaw) throws Exception {
        // Create ECDSA key factory
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        
        // Convert raw private key bytes to PrivateKey object
        // Assuming the raw key is a 32-byte private key value
        BigInteger privateKeyValue = new BigInteger(1, signingKeyRaw);
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privateKeyValue, getP256Spec());
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        
        // Create signature instance
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(message);
        
        return signature.sign();
    }
    
    public static boolean verifyNist256(byte[] message, byte[] signatureBytes, byte[] verifyingKeyRaw) {
        try {
            // Create ECDSA key factory
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            
            // Convert raw public key bytes to PublicKey object
            // Assuming the raw key is uncompressed format (65 bytes: 0x04 + 32 bytes x + 32 bytes y)
            // or compressed format (33 bytes)
            ECPoint publicPoint = decodePublicKey(verifyingKeyRaw);
            ECPublicKeySpec publicKeySpec = new ECPublicKeySpec(publicPoint, getP256Spec());
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            
            // Create signature instance
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initVerify(publicKey);
            signature.update(message);
            
            return signature.verify(signatureBytes);
        } catch (Exception e) {
            return false;
        }
    }
    
    private static ECParameterSpec getP256Spec() {
        // NIST P-256 curve parameters
        BigInteger p = new BigInteger("115792089210356248762697446949407573530086143415290314195533631308867097853951");
        BigInteger a = new BigInteger("115792089210356248762697446949407573530086143415290314195533631308867097853948");
        BigInteger b = new BigInteger("41058363725152142129326129780047268409114441015993725554835256314039467401291");
        BigInteger gx = new BigInteger("48439561293906451759052585252797914202762949526041747995844080717082404635286");
        BigInteger gy = new BigInteger("36134250956749795798585127919587881956611106672985015071877198253568414405109");
        BigInteger n = new BigInteger("115792089210356248762697446949407573529996955224135760342422259061068512044369");
        int h = 1;
        
        EllipticCurve curve = new EllipticCurve(new ECFieldFp(p), a, b);
        ECPoint generator = new ECPoint(gx, gy);
        
        return new ECParameterSpec(curve, generator, n, h);
    }
    
    private static ECPoint decodePublicKey(byte[] keyBytes) {
        if (keyBytes.length == 65 && keyBytes[0] == 0x04) {
            // Uncompressed format: 0x04 || x || y
            byte[] xBytes = new byte[32];
            byte[] yBytes = new byte[32];
            System.arraycopy(keyBytes, 1, xBytes, 0, 32);
            System.arraycopy(keyBytes, 33, yBytes, 0, 32);
            
            BigInteger x = new BigInteger(1, xBytes);
            BigInteger y = new BigInteger(1, yBytes);
            
            return new ECPoint(x, y);
        } else if (keyBytes.length == 33 && (keyBytes[0] == 0x02 || keyBytes[0] == 0x03)) {
            // Compressed format - need to decompress
            throw new IllegalArgumentException("Compressed public key format not implemented in this example");
        } else if (keyBytes.length == 64) {
            // Raw format without prefix: x || y
            byte[] xBytes = new byte[32];
            byte[] yBytes = new byte[32];
            System.arraycopy(keyBytes, 0, xBytes, 0, 32);
            System.arraycopy(keyBytes, 32, yBytes, 0, 32);
            
            BigInteger x = new BigInteger(1, xBytes);
            BigInteger y = new BigInteger(1, yBytes);
            
            return new ECPoint(x, y);
        } else {
            throw new IllegalArgumentException("Invalid public key format");
        }
    }
}