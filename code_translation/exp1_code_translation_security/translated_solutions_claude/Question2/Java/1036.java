import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.math.BigInteger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.ASN1EncodableVector;
import java.security.Security;

public class ECDSASignature {
    
    static {
        // Add BouncyCastle provider
        Security.addProvider(new BouncyCastleProvider());
    }
    
    public static String signMessage(String message, byte[] privateKey) {
        try {
            // Hash the message using SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] messageHash = digest.digest(message.getBytes("UTF-8"));
            
            // Get SECP256k1 curve parameters
            ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
            ECDomainParameters domainParams = new ECDomainParameters(
                ecSpec.getCurve(), 
                ecSpec.getG(), 
                ecSpec.getN(), 
                ecSpec.getH()
            );
            
            // Create private key from byte array
            BigInteger privateKeyInt = new BigInteger(1, privateKey);
            ECPrivateKeyParameters privKeyParams = new ECPrivateKeyParameters(privateKeyInt, domainParams);
            
            // Sign the message hash
            ECDSASigner signer = new ECDSASigner();
            signer.init(true, privKeyParams);
            BigInteger[] signature = signer.generateSignature(messageHash);
            
            // Encode signature in DER format
            ASN1EncodableVector v = new ASN1EncodableVector();
            v.add(new ASN1Integer(signature[0]));
            v.add(new ASN1Integer(signature[1]));
            DERSequence derSequence = new DERSequence(v);
            byte[] derSignature = derSequence.getEncoded();
            
            // Convert to hex string
            return bytesToHex(derSignature);
            
        } catch (Exception e) {
            throw new RuntimeException("Error signing message", e);
        }
    }
    
    // Helper method to convert bytes to hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
    
    // Helper method to convert hex string to bytes
    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                                + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
    
    public static void main(String[] args) {
        // Test the function
        String privateKeyHex = "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
        byte[] privateKey = hexToBytes(privateKeyHex);
        String message = "Hello";
        String signature = signMessage(message, privateKey);
        System.out.println(signature);
    }
}