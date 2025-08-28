import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Signature;
import java.nio.charset.StandardCharsets;

public class EcdsaExample {

    public static void main(String[] args) throws Exception {
        // Generate EC key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(256); // Using P-256 curve
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        String message = "My test message";
        String signedMsg = generateEcdsaSignedMsg(message, privateKey);
        System.out.println(signedMsg);
    }

    public static String generateEcdsaSignedMsg(String message, PrivateKey privateKey) throws Exception {
        Signature sig = Signature.getInstance("SHA256withECDSA");
        sig.initSign(privateKey);
        sig.update(message.getBytes(StandardCharsets.UTF_8));
        byte[] signatureBytes = sig.sign();

        return parseSignatureToJSON(signatureBytes);
    }

    private static String parseSignatureToJSON(byte[] sigBytes) {
        int offset = 0;

        // Check DER sequence start byte
        if (sigBytes[offset++] != 0x30) {
            throw new IllegalArgumentException("Invalid DER signature");
        }

        // Skip length (assume single byte length)
        offset++;

        // Parse r
        if (sigBytes[offset++] != 0x02) {
            throw new IllegalArgumentException("Invalid DER signature: no r");
        }
        int rLength = sigBytes[offset++] & 0xFF;
        byte[] rBytes = new byte[rLength];
        System.arraycopy(sigBytes, offset, rBytes, 0, rLength);
        offset += rLength;

        // Parse s
        if (sigBytes[offset++] != 0x02) {
            throw new IllegalArgumentException("Invalid DER signature: no s");
        }
        int sLength = sigBytes[offset++] & 0xFF;
        byte[] sBytes = new byte[sLength];
        System.arraycopy(sigBytes, offset, sBytes, 0, sLength);

        // Convert to hex strings
        String rHex = bytesToHex(rBytes);
        String sHex = bytesToHex(sBytes);

        return String.format("{\"r\": \"%s\", \"s\": \"%s\"}", rHex, sHex);
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}