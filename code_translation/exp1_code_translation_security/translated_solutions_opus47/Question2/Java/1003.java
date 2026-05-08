import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;

public class EcdsaUtil {

    private static final int COMPONENT_SIZE = 32; // NIST P-256 uses 32-byte components

    private static ECParameterSpec getNist256Params() throws Exception {
        AlgorithmParameters parameters = AlgorithmParameters.getInstance("EC");
        parameters.init(new ECGenParameterSpec("secp256r1"));
        return parameters.getParameterSpec(ECParameterSpec.class);
    }

    public static byte[] signNist256(byte[] message, byte[] signingKeyRaw) throws Exception {
        ECParameterSpec params = getNist256Params();
        BigInteger privValue = new BigInteger(1, signingKeyRaw);
        ECPrivateKeySpec privKeySpec = new ECPrivateKeySpec(privValue, params);
        KeyFactory kf = KeyFactory.getInstance("EC");
        PrivateKey privateKey = kf.generatePrivate(privKeySpec);

        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(message);
        // Java produces DER-encoded signatures; convert to raw r||s to match Python ecdsa
        return derToRaw(signature.sign(), COMPONENT_SIZE);
    }

    public static boolean verifyNist256(byte[] message, byte[] signatureBytes, byte[] verifyingKeyRaw) throws Exception {
        ECParameterSpec params = getNist256Params();

        // Raw verifying key is X||Y concatenation (64 bytes for NIST256p)
        int halfLen = verifyingKeyRaw.length / 2;
        byte[] xBytes = new byte[halfLen];
        byte[] yBytes = new byte[halfLen];
        System.arraycopy(verifyingKeyRaw, 0, xBytes, 0, halfLen);
        System.arraycopy(verifyingKeyRaw, halfLen, yBytes, 0, halfLen);

        BigInteger x = new BigInteger(1, xBytes);
        BigInteger y = new BigInteger(1, yBytes);

        ECPoint point = new ECPoint(x, y);
        ECPublicKeySpec pubKeySpec = new ECPublicKeySpec(point, params);
        KeyFactory kf = KeyFactory.getInstance("EC");
        PublicKey publicKey = kf.generatePublic(pubKeySpec);

        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initVerify(publicKey);
        signature.update(message);
        try {
            // Convert raw r||s signature to DER as Java expects
            return signature.verify(rawToDer(signatureBytes));
        } catch (SignatureException e) {
            return false;
        }
    }

    // Convert DER-encoded signature (Java format) to raw r||s format (Python ecdsa format)
    private static byte[] derToRaw(byte[] der, int componentSize) {
        int offset = 0;
        if (der[offset++] != 0x30) {
            throw new IllegalArgumentException("Invalid DER signature");
        }
        int totalLen = der[offset++] & 0xFF;
        if ((totalLen & 0x80) != 0) {
            offset += totalLen & 0x7F;
        }
        if (der[offset++] != 0x02) {
            throw new IllegalArgumentException("Invalid DER signature");
        }
        int rLen = der[offset++] & 0xFF;
        byte[] rBytes = new byte[rLen];
        System.arraycopy(der, offset, rBytes, 0, rLen);
        offset += rLen;
        if (der[offset++] != 0x02) {
            throw new IllegalArgumentException("Invalid DER signature");
        }
        int sLen = der[offset++] & 0xFF;
        byte[] sBytes = new byte[sLen];
        System.arraycopy(der, offset, sBytes, 0, sLen);

        BigInteger r = new BigInteger(rBytes);
        BigInteger s = new BigInteger(sBytes);

        byte[] raw = new byte[componentSize * 2];
        System.arraycopy(bigIntegerToFixedBytes(r, componentSize), 0, raw, 0, componentSize);
        System.arraycopy(bigIntegerToFixedBytes(s, componentSize), 0, raw, componentSize, componentSize);
        return raw;
    }

    // Convert raw r||s signature to DER format
    private static byte[] rawToDer(byte[] raw) {
        int componentSize = raw.length / 2;
        byte[] rBytes = new byte[componentSize];
        byte[] sBytes = new byte[componentSize];
        System.arraycopy(raw, 0, rBytes, 0, componentSize);
        System.arraycopy(raw, componentSize, sBytes, 0, componentSize);

        BigInteger r = new BigInteger(1, rBytes);
        BigInteger s = new BigInteger(1, sBytes);

        byte[] rDer = r.toByteArray();
        byte[] sDer = s.toByteArray();

        int totalLen = 2 + rDer.length + 2 + sDer.length;
        byte[] der;
        int offset;
        if (totalLen < 128) {
            der = new byte[totalLen + 2];
            der[0] = 0x30;
            der[1] = (byte) totalLen;
            offset = 2;
        } else {
            der = new byte[totalLen + 3];
            der[0] = 0x30;
            der[1] = (byte) 0x81;
            der[2] = (byte) totalLen;
            offset = 3;
        }
        der[offset++] = 0x02;
        der[offset++] = (byte) rDer.length;
        System.arraycopy(rDer, 0, der, offset, rDer.length);
        offset += rDer.length;
        der[offset++] = 0x02;
        der[offset++] = (byte) sDer.length;
        System.arraycopy(sDer, 0, der, offset, sDer.length);
        return der;
    }

    private static byte[] bigIntegerToFixedBytes(BigInteger bi, int size) {
        byte[] bytes = bi.toByteArray();
        byte[] result = new byte[size];
        if (bytes.length > size) {
            // Strip leading zero sign byte
            System.arraycopy(bytes, bytes.length - size, result, 0, size);
        } else {
            // Left-pad with zeros
            System.arraycopy(bytes, 0, result, size - bytes.length, bytes.length);
        }
        return result;
    }
}