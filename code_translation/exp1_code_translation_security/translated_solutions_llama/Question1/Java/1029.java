import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Arrays;

public class AEScipher {

    private byte[] key;
    private int blk_sz;

    /**
     * Constructor for AEScipher class.
     * 
     * @param key     The encryption key.
     * @param blk_sz  The block size for AES encryption.
     */
    public AEScipher(byte[] key, int blk_sz) {
        this.key = key;
        this.blk_sz = blk_sz;
    }

    /**
     * Encrypts a message using AES encryption.
     * 
     * @param msg  The message to be encrypted.
     * @return     The encrypted message.
     * @throws Exception  If an error occurs during encryption.
     */
    public byte[] encrypt(String msg) throws Exception {
        // Convert message to bytes
        byte[] msgBytes = msg.getBytes();

        // Padding
        int pad_len = blk_sz - (msgBytes.length % blk_sz);
        byte[] paddedMsg = new byte[msgBytes.length + pad_len];
        System.arraycopy(msgBytes, 0, paddedMsg, 0, msgBytes.length);
        for (int i = msgBytes.length; i < paddedMsg.length; i++) {
            paddedMsg[i] = (byte) pad_len;
        }

        // Encryption
        Key secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(paddedMsg);
    }

    /**
     * Decrypts a ciphertext using AES decryption.
     * 
     * @param ciphertext  The ciphertext to be decrypted.
     * @return            The decrypted message.
     * @throws Exception  If an error occurs during decryption.
     */
    public String decrypt(byte[] ciphertext) throws Exception {
        // Decryption
        Key secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(ciphertext);

        // Remove padding
        int pad_len = decryptedBytes[decryptedBytes.length - 1];
        byte[] unpaddedMsg = new byte[decryptedBytes.length - pad_len];
        System.arraycopy(decryptedBytes, 0, unpaddedMsg, 0, unpaddedMsg.length);

        return new String(unpaddedMsg);
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        byte[] key = "mysecretkey1234".getBytes();
        AEScipher aes = new AEScipher(key, 16);
        String msg = "Hello, World!";
        byte[] ciphertext = aes.encrypt(msg);
        System.out.println("Encrypted: " + Arrays.toString(ciphertext));
        String decryptedMsg = aes.decrypt(ciphertext);
        System.out.println("Decrypted: " + decryptedMsg);
    }
}