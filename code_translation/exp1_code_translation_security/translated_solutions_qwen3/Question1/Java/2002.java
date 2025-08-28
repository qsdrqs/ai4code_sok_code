import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AESCTR {

    /**
     * Encrypts a string using AES-CTR with a 256-bit key.
     *
     * @param k 256-bit key as a byte array
     * @param m Plaintext string to encrypt
     * @return Encrypted byte array
     * @throws Exception If an error occurs during encryption
     */
    public static byte[] encrypt(byte[] k, String m) throws Exception {
        byte[] plaintext = m.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(k, "AES"));

        byte[] output = new byte[plaintext.length];
        long counter = 0;
        int offset = 0;

        while (offset < plaintext.length) {
            byte[] counterBlock = createCounterBlock(counter++);
            byte[] encryptedBlock = cipher.doFinal(counterBlock);

            int blockLength = Math.min(plaintext.length - offset, 16);
            for (int i = 0; i < blockLength; i++) {
                output[offset + i] = (byte) (plaintext[offset + i] ^ encryptedBlock[i]);
            }
            offset += blockLength;
        }

        return output;
    }

    /**
     * Decrypts a byte array using AES-CTR with a 256-bit key.
     *
     * @param k 256-bit key as a byte array
     * @param ct Ciphertext byte array to decrypt
     * @return Decrypted string
     * @throws Exception If an error occurs during decryption
     */
    public static String decrypt(byte[] k, byte[] ct) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(k, "AES"));

        byte[] output = new byte[ct.length];
        long counter = 0;
        int offset = 0;

        while (offset < ct.length) {
            byte[] counterBlock = createCounterBlock(counter++);
            byte[] encryptedBlock = cipher.doFinal(counterBlock);

            int blockLength = Math.min(ct.length - offset, 16);
            for (int i = 0; i < blockLength; i++) {
                output[offset + i] = (byte) (ct[offset + i] ^ encryptedBlock[i]);
            }
            offset += blockLength;
        }

        return new String(output, StandardCharsets.UTF_8);
    }

    /**
     * Creates a 16-byte counter block with the first 8 bytes as a little-endian 64-bit integer.
     *
     * @param value The counter value
     * @return A 16-byte counter block
     */
    private static byte[] createCounterBlock(long value) {
        byte[] block = new byte[16];
        for (int i = 0; i < 8; i++) {
            block[i] = (byte) (value >> (i * 8));
        }
        // Remaining 8 bytes are zero
        return block;
    }

    /**
     * Test method to verify encryption and decryption.
     */
    public static void test() {
        try {
            roundTripTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs a round-trip test of encryption and decryption.
     */
    private static void roundTripTest() throws Exception {
        byte[] k = "This_key_for_demo_purposes_only!".getBytes(StandardCharsets.UTF_8);
        String plaintext = "Text may be any length you wish, no padding is required";
        byte[] ciphertext = encrypt(k, plaintext);
        String decrypted = decrypt(k, ciphertext);
        System.out.println(decrypted);
    }

    /**
     * Main method to run the test.
     */
    public static void main(String[] args) {
        test();
    }
}