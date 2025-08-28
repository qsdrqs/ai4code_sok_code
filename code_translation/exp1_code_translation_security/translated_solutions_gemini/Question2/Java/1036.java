import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;

public class EcdsaSigner {

    // A static block to add the Bouncy Castle provider automatically.
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Signs a message using a private key with ECDSA (secp256k1).
     *
     * @param message The message string to sign.
     * @param privateKeyBytes The raw 32 bytes of the private key.
     * @return The DER-encoded signature as a hex string.
     * @throws Exception if any cryptographic error occurs.
     */
    public static String signMessage(String message, byte[] privateKeyBytes) throws Exception {
        // 1. Hash the message using SHA-256
        // This is equivalent to Python's hashlib.sha256(message.encode('utf-8')).digest()
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = digest.digest(message.getBytes(StandardCharsets.UTF_8));

        // 2. Create the private key object from raw bytes
        // This is equivalent to Python's ecdsa.SigningKey.from_string(..., curve=ecdsa.SECP256k1)
        ECNamedCurveParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(new BigInteger(1, privateKeyBytes), ecSpec);
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // 3. Sign the message hash
        // We use "NONEwithECDSA" because we are signing a pre-computed hash, not the raw message.
        // This is equivalent to Python's signing_key.sign_digest(...)
        // The output is DER-encoded by default, matching sigencode=ecdsa.util.sigencode_der
        Signature signature = Signature.getInstance("NONEwithECDSA", "BC");
        signature.initSign(privateKey);
        signature.update(messageHash);
        byte[] derSignature = signature.sign();

        // 4. Convert the signature to a hex string and return
        // This is equivalent to Python's binascii.hexlify(signature).decode('utf-8')
        return bytesToHex(derSignature);
    }

    /**
     * Helper method to convert a byte array to a hexadecimal string.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Helper method to convert a hexadecimal string to a byte array.
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static void main(String[] args) {
        try {
            // Test the function with the same values as the Python script
            String privateKeyHex = "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109";
            byte[] privateKey = hexStringToByteArray(privateKeyHex);
            String message = "Hello";

            String signature = signMessage(message, privateKey);
            System.out.println(signature);
            // Expected output: 3045022100801209c80519990025132a42434c423a088811335081112237129011015243022035a333232613e2012473621542840597a3381360901134226984514338241824
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}