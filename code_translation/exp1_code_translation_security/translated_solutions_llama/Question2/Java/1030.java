import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.security.SecureRandom;
import java.util.Base64;

public class ECDSASigning {

    // Define the curve and hash function
    private static final String CURVE = "secp256k1";
    private static final String HASH_ALGORITHM = "SHA3-256";

    public static void main(String[] args) throws Exception {
        // ECDSA sign message (using the curve secp256k1 + SHA3-256)
        String msg = "Message for ECDSA signing";

        // Generate a random private key
        SecureRandom secureRandom = new SecureRandom();
        X9ECParameters params = SECNamedCurves.getByName(CURVE);
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        // Generate a random private key
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParams, secureRandom));
        AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();
        ECPrivateKeyParameters privateKey = (ECPrivateKeyParameters) keyPair.getPrivate();
        ECPublicKeyParameters publicKey = (ECPublicKeyParameters) keyPair.getPublic();

        // Sign the message
        byte[] signatureBytes = signECDSAsecp256k1(msg, privateKey, domainParams);

        // Verify the signature
        boolean isValid = verifyECDSAsecp256k1(msg, signatureBytes, publicKey, domainParams);

        System.out.println("Message: " + msg);
        System.out.println("Private key: " + bytesToHex(privateKey.getD().toByteArray()));
        System.out.println("Public key: " + bytesToHex(publicKey.getQ().getEncoded(false)));
        System.out.println("Signature: " + bytesToHex(signatureBytes));
        System.out.println("Is signature valid? " + isValid);
    }

    private static byte[] signECDSAsecp256k1(String msg, ECPrivateKeyParameters privateKey, ECDomainParameters domainParams) throws Exception {
        // Hash the message
        byte[] msgHash = sha3_256Hash(msg);

        // Sign the message
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privateKey);
        BigInteger[] signature = signer.generateSignature(msgHash);

        // Convert the signature to DER format
        return derEncode(signature[0], signature[1], domainParams.getN());
    }

    private static boolean verifyECDSAsecp256k1(String msg, byte[] signatureBytes, ECPublicKeyParameters publicKey, ECDomainParameters domainParams) throws Exception {
        // Hash the message
        byte[] msgHash = sha3_256Hash(msg);

        // Parse the DER-encoded signature
        BigInteger[] signature = derDecode(signatureBytes, domainParams.getN());

        // Verify the signature
        ECDSASigner signer = new ECDSASigner();
        signer.init(false, publicKey);
        return signer.verifySignature(msgHash, signature[0], signature[1]);
    }

    private static byte[] sha3_256Hash(String msg) throws Exception {
        // Use the SHA3-256 hash function from Java
        java.security.MessageDigest md = java.security.MessageDigest.getInstance(HASH_ALGORITHM);
        return md.digest(msg.getBytes("UTF-8"));
    }

    private static byte[] derEncode(BigInteger r, BigInteger s, BigInteger n) {
        // DER encoding of the signature
        int rLen = r.toByteArray().length;
        int sLen = s.toByteArray().length;
        int len = 2 + rLen + 2 + sLen;

        byte[] der = new byte[len];
        int pos = 0;

        der[pos++] = 0x30; // SEQUENCE
        der[pos++] = (byte) (len - 2); // length

        der[pos++] = 0x02; // INTEGER
        der[pos++] = (byte) rLen;
        System.arraycopy(r.toByteArray(), 0, der, pos, rLen);
        pos += rLen;

        der[pos++] = 0x02; // INTEGER
        der[pos++] = (byte) sLen;
        System.arraycopy(s.toByteArray(), 0, der, pos, sLen);
        pos += sLen;

        return der;
    }

    private static BigInteger[] derDecode(byte[] der, BigInteger n) {
        int pos = 0;

        if (der[pos++] != 0x30) {
            throw new RuntimeException("Invalid DER encoding");
        }

        int len = der[pos++];
        if (pos + len != der.length) {
            throw new RuntimeException("Invalid DER encoding");
        }

        BigInteger r = derDecodeInt(der, pos);
        pos += 2 + r.toByteArray().length;

        BigInteger s = derDecodeInt(der, pos);

        // Ensure r and s are in the range [1, n-1]
        if (r.compareTo(BigInteger.ONE) < 0 || r.compareTo(n.subtract(BigInteger.ONE)) >= 0) {
            r = n.subtract(r);
        }
        if (s.compareTo(BigInteger.ONE) < 0 || s.compareTo(n.subtract(BigInteger.ONE)) >= 0) {
            s = n.subtract(s);
        }

        return new BigInteger[] {r, s};
    }

    private static BigInteger derDecodeInt(byte[] der, int pos) {
        if (der[pos++] != 0x02) {
            throw new RuntimeException("Invalid DER encoding");
        }

        int len = der[pos++];
        byte[] bytes = new byte[len];
        System.arraycopy(der, pos, bytes, 0, len);

        return new BigInteger(1, bytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}