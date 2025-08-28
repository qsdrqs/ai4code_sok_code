/*
 *  A very small, self–contained Java port of the Python code shown in the
 *  question.  The behaviour – including the slightly odd “always-100_000
 *  iterations for the key that is used during encryption” – is preserved.
 *
 *  All classes that are used (`javax.crypto.*`, `java.security.*`,
 *  `java.util.*`, `java.nio.*` …) are part of the standard JDK.  No third
 *  party dependency is required because a minimal Fernet implementation is
 *  included below.  Should you already have a Fernet implementation on the
 *  class-path you can of course replace the inner ​Fernet​ class with the
 *  one you prefer – everything else will work unchanged.
 */
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PBEKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class PythonCryptoPort {

    /* -------------------------------------------------------------------- */
    /*  PBKDF-2 / key-derivation helpers                                    */
    /* -------------------------------------------------------------------- */

    private static final SecureRandom RNG = new SecureRandom();

    /**
     * Derive a 32-byte secret and return it URL-safe-base64 encoded
     * (exactly what Python’s  ``urlsafe_b64encode`` does).
     */
    private static byte[] deriveKey(final byte[] password,
                                    final byte[] salt,
                                    final int iterations) throws GeneralSecurityException {

        final PBEKeySpec spec = new PBEKeySpec(
                new String(password, StandardCharsets.UTF_8).toCharArray(),
                salt,
                iterations,
                32 * 8                // 32 bytes  →  256 bits
        );

        final SecretKeyFactory factory =
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        final byte[] rawKey = factory.generateSecret(spec).getEncoded();

        // url-safe Base64 – padding kept because Python keeps it as well
        return Base64.getUrlEncoder().encode(rawKey);
    }

    /* -------------------------------------------------------------------- */
    /*  Public encrypt / decrypt API                                        */
    /* -------------------------------------------------------------------- */

    public static String encrypt(final String message,
                                 final String password) throws GeneralSecurityException {
        return encrypt(message, password, 100_000);
    }

    public static String encrypt(final String message,
                                 final String password,
                                 final int iterations) throws GeneralSecurityException {

        final byte[] plaintext = message.getBytes(StandardCharsets.UTF_8);

        // 16-byte salt – just like the Python version
        final byte[] salt = new byte[16];
        RNG.nextBytes(salt);

        /* This is the quirk in the original code:
           The supplied 'iterations' value is *not* used, 100_000 always is   */
        final byte[] keyEncoded = deriveKey(
                password.getBytes(StandardCharsets.UTF_8), salt, 100_000);

        final Fernet fernet = new Fernet(
                new String(keyEncoded, StandardCharsets.US_ASCII));

        /* Fernet gives us a URL-safe Base64 token.  The Python code removes
           that outer Base64 layer, packs salt + iterations + <raw-token>,
           then Base64-encodes the whole thing again.                        */
        final byte[] fernetRaw = Base64.getUrlDecoder()
                                      .decode(fernet.encrypt(plaintext));

        final byte[] iterBytes = ByteBuffer.allocate(4)
                                           .putInt(iterations)   // big-endian
                                           .array();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.write(salt);
            baos.write(iterBytes);
            baos.write(fernetRaw);
        } catch (java.io.IOException cannotHappen) { /* ignore */ }

        return Base64.getUrlEncoder().encodeToString(baos.toByteArray());
    }

    public static String decrypt(final String token,
                                 final String password) throws GeneralSecurityException {

        final byte[] decoded = Base64.getUrlDecoder().decode(token);

        final byte[] salt      = Arrays.copyOfRange(decoded, 0, 16);
        final byte[] iterBytes = Arrays.copyOfRange(decoded, 16, 20);
        final int    iterations =
                ByteBuffer.wrap(iterBytes).getInt();           // big-endian

        final byte[] fernetRaw = Arrays.copyOfRange(decoded, 20, decoded.length);

        final byte[] keyEncoded = deriveKey(
                password.getBytes(StandardCharsets.UTF_8), salt, iterations);

        final Fernet fernet = new Fernet(
                new String(keyEncoded, StandardCharsets.US_ASCII));

        final byte[] plaintext = fernet.decrypt(
                Base64.getUrlEncoder().encodeToString(fernetRaw));

        return new String(plaintext, StandardCharsets.UTF_8);
    }

    /* -------------------------------------------------------------------- */
    /*  A *very* small Fernet implementation                                */
    /* -------------------------------------------------------------------- */

    private static final class Fernet {

        private static final byte VERSION = (byte) 0x80;      // constant prefix

        private final byte[] encryptionKey;                   // first 16 bytes
        private final byte[] signingKey;                      // last  16 bytes

        Fernet(final String base64UrlKey) {
            final byte[] key = Base64.getUrlDecoder().decode(base64UrlKey);
            if (key.length != 32) {
                throw new IllegalArgumentException(
                        "Fernet key must decode to exactly 32 bytes");
            }
            encryptionKey = Arrays.copyOfRange(key, 0, 16);
            signingKey    = Arrays.copyOfRange(key, 16, 32);
        }

        /* ---------------------------- encryption ------------------------- */

        String encrypt(final byte[] plaintext) throws GeneralSecurityException {

            final long timestamp = System.currentTimeMillis() / 1_000L;

            final byte[] iv = new byte[16];
            RNG.nextBytes(iv);

            /* AES-128-CBC + PKCS#5 padding */
            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,
                        new SecretKeySpec(encryptionKey, "AES"),
                        new IvParameterSpec(iv));

            final byte[] ciphertext = cipher.doFinal(plaintext);

            /* version ‑- timestamp ‑- iv ‑- ciphertext */
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(VERSION);
            baos.write(ByteBuffer.allocate(8).putLong(timestamp).array());
            baos.write(iv);
            baos.write(ciphertext);
            final byte[] dataToMac = baos.toByteArray();

            /* HMAC-SHA-256 */
            final Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(signingKey, "HmacSHA256"));
            final byte[] hmac = mac.doFinal(dataToMac);

            baos.write(hmac);
            return Base64.getUrlEncoder().encodeToString(baos.toByteArray());
        }

        /* ---------------------------- decryption ------------------------- */

        byte[] decrypt(final String token) throws GeneralSecurityException {

            final byte[] decoded = Base64.getUrlDecoder().decode(token);

            if (decoded[0] != VERSION) {
                throw new SecurityException("Invalid Fernet token – bad version");
            }

            final ByteBuffer bb = ByteBuffer.wrap(decoded);
            bb.get();                          // skip version byte
            bb.getLong();                      // timestamp (unused here)

            final byte[] iv = new byte[16];
            bb.get(iv);

            final byte[] rest = new byte[bb.remaining()];
            bb.get(rest);

            final byte[] ciphertext = Arrays.copyOfRange(rest, 0, rest.length - 32);
            final byte[] hmac       = Arrays.copyOfRange(rest, rest.length - 32, rest.length);

            /* constant-time MAC check */
            final Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(signingKey, "HmacSHA256"));

            final byte[] expectedMac = mac.doFinal(Arrays.copyOf(decoded,
                                                                 decoded.length - 32));

            if (!MessageDigest.isEqual(hmac, expectedMac)) {
                throw new SecurityException("Invalid Fernet token – bad HMAC");
            }

            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,
                        new SecretKeySpec(encryptionKey, "AES"),
                        new IvParameterSpec(iv));

            return cipher.doFinal(ciphertext);
        }
    }

    /* -------------------------------------------------------------------- */
    /*  Quick demo – prints a token and the decrypted plaintext             */
    /* -------------------------------------------------------------------- */

    public static void main(String[] args) throws Exception {

        final String token = encrypt("nice123", "password123");

        System.out.println(token);                           // encrypted blob
        System.out.println(decrypt(token, "password123"));   // →  nice123
    }
}