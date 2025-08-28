import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ECDSASecp256k1 {
    
    // secp256k1 curve parameters
    private static final BigInteger P = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16);
    private static final BigInteger N = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16);
    private static final BigInteger A = BigInteger.ZERO;
    private static final BigInteger B = BigInteger.valueOf(7);
    private static final BigInteger GX = new BigInteger("79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798", 16);
    private static final BigInteger GY = new BigInteger("483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8", 16);
    
    // Point class to represent elliptic curve points
    static class Point {
        BigInteger x, y;
        boolean isInfinity;
        
        Point() {
            this.isInfinity = true;
        }
        
        Point(BigInteger x, BigInteger y) {
            this.x = x;
            this.y = y;
            this.isInfinity = false;
        }
    }
    
    // Modular inverse using extended Euclidean algorithm
    private static BigInteger modInverse(BigInteger a, BigInteger m) {
        return a.modInverse(m);
    }
    
    // Point addition on elliptic curve
    private static Point pointAdd(Point p1, Point p2) {
        if (p1.isInfinity) return p2;
        if (p2.isInfinity) return p1;
        
        if (p1.x.equals(p2.x)) {
            if (p1.y.equals(p2.y)) {
                // Point doubling
                BigInteger s = p1.x.multiply(p1.x).multiply(BigInteger.valueOf(3)).add(A)
                    .multiply(modInverse(p1.y.multiply(BigInteger.valueOf(2)), P)).mod(P);
                BigInteger x3 = s.multiply(s).subtract(p1.x.multiply(BigInteger.valueOf(2))).mod(P);
                BigInteger y3 = s.multiply(p1.x.subtract(x3)).subtract(p1.y).mod(P);
                return new Point(x3, y3);
            } else {
                return new Point(); // Point at infinity
            }
        } else {
            BigInteger s = p2.y.subtract(p1.y).multiply(modInverse(p2.x.subtract(p1.x), P)).mod(P);
            BigInteger x3 = s.multiply(s).subtract(p1.x).subtract(p2.x).mod(P);
            BigInteger y3 = s.multiply(p1.x.subtract(x3)).subtract(p1.y).mod(P);
            return new Point(x3, y3);
        }
    }
    
    // Scalar multiplication using double-and-add
    private static Point pointMultiply(BigInteger k, Point point) {
        if (k.equals(BigInteger.ZERO)) return new Point();
        if (k.equals(BigInteger.ONE)) return point;
        
        Point result = new Point();
        Point addend = point;
        
        while (k.compareTo(BigInteger.ZERO) > 0) {
            if (k.testBit(0)) {
                result = pointAdd(result, addend);
            }
            addend = pointAdd(addend, addend);
            k = k.shiftRight(1);
        }
        
        return result;
    }
    
    public static BigInteger sha3_256Hash(String msg) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            byte[] hashBytes = digest.digest(msg.getBytes(StandardCharsets.UTF_8));
            return new BigInteger(1, hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA3-256 not available", e);
        }
    }
    
    public static BigInteger[] signECDSAsecp256k1(String msg, BigInteger privKey) {
        BigInteger msgHash = sha3_256Hash(msg);
        SecureRandom random = new SecureRandom();
        
        while (true) {
            BigInteger k = new BigInteger(256, random).mod(N.subtract(BigInteger.ONE)).add(BigInteger.ONE);
            Point kG = pointMultiply(k, new Point(GX, GY));
            BigInteger r = kG.x.mod(N);
            
            if (r.equals(BigInteger.ZERO)) continue;
            
            BigInteger kInv = modInverse(k, N);
            BigInteger s = kInv.multiply(msgHash.add(privKey.multiply(r))).mod(N);
            
            if (s.equals(BigInteger.ZERO)) continue;
            
            return new BigInteger[]{r, s};
        }
    }
    
    public static boolean verifyECDSAsecp256k1(String msg, BigInteger[] signature, Point pubKey) {
        BigInteger msgHash = sha3_256Hash(msg);
        BigInteger r = signature[0];
        BigInteger s = signature[1];
        
        if (r.compareTo(BigInteger.ONE) < 0 || r.compareTo(N) >= 0) return false;
        if (s.compareTo(BigInteger.ONE) < 0 || s.compareTo(N) >= 0) return false;
        
        BigInteger sInv = modInverse(s, N);
        BigInteger u1 = msgHash.multiply(sInv).mod(N);
        BigInteger u2 = r.multiply(sInv).mod(N);
        
        Point point1 = pointMultiply(u1, new Point(GX, GY));
        Point point2 = pointMultiply(u2, pubKey);
        Point result = pointAdd(point1, point2);
        
        if (result.isInfinity) return false;
        
        return result.x.mod(N).equals(r);
    }
    
    public static void main(String[] args) {
        // ECDSA sign message (using the curve secp256k1 + SHA3-256)
        String msg = "Message for ECDSA signing";
        
        // Generate private key (random number less than curve order)
        SecureRandom random = new SecureRandom();
        BigInteger privKey = new BigInteger(256, random).mod(N.subtract(BigInteger.ONE)).add(BigInteger.ONE);
        
        // Sign the message
        BigInteger[] signature = signECDSAsecp256k1(msg, privKey);
        
        // Generate public key for verification
        Point pubKey = pointMultiply(privKey, new Point(GX, GY));
        
        System.out.println("Message: " + msg);
        System.out.println("Private key: 0x" + privKey.toString(16));
        System.out.println("Signature: r=0x" + signature[0].toString(16) + ", s=0x" + signature[1].toString(16));
        
        // Verify the signature
        boolean isValid = verifyECDSAsecp256k1(msg, signature, pubKey);
        System.out.println("Signature valid: " + isValid);
    }
}