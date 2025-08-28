import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecP256K1Curve;
import org.bouncycastle.math.ec.custom.sec.SecP256K1Point;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.Security;

public class ECDSASecp256k1Example {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final ECDomainParameters SECP256K1_DOMAIN;

    static {
        SecP256K1Curve curve = new SecP256K1Curve();
        ECPoint G = curve.decodePoint(SecP256K1Curve.q);
        BigInteger n = curve.getOrder();
        BigInteger h = curve.getCofactor();
        SECP256K1_DOMAIN = new ECDomainParameters(curve, G, n, h);
    }

    public static BigInteger sha3_256Hash(String msg) {
        SHA3Digest digest = new SHA3Digest(256);
        byte[] msgBytes = msg.getBytes();
        digest.update(msgBytes, 0, msgBytes.length);
        byte[] hashBytes = new byte[digest.getDigestSize()];
        digest.doFinal(hashBytes, 0);
        return new BigInteger(1, hashBytes);
    }

    public static BigInteger[] signECDSAsecp256k1(String msg, BigInteger privKey) {
        BigInteger msgHash = sha3_256Hash(msg);
        ECDSASigner signer = new ECDSASigner();
        ECPrivateKeyParameters privKeyParams = new ECPrivateKeyParameters(privKey, SECP256K1_DOMAIN);
        signer.init(true, privKeyParams);
        return signer.generateSignature(msgHash.toByteArray());
    }

    public static boolean verifyECDSAsecp256k1(String msg, BigInteger[] signature, ECPoint pubKey) {
        BigInteger msgHash = sha3_256Hash(msg);
        ECDSASigner signer = new ECDSASigner();
        ECPublicKeyParameters pubKeyParams = new ECPublicKeyParameters(pubKey, SECP256K1_DOMAIN);
        signer.init(false, pubKeyParams);
        return signer.verifySignature(msgHash.toByteArray(), signature[0], signature[1]);
    }

    public static void main(String[] args) {
        String msg = "Message for ECDSA signing";
        SecureRandom random = new SecureRandom();
        BigInteger privKey = new BigInteger(SECP256K1_DOMAIN.getN().bitLength(), random).mod(SECP256K1_DOMAIN.getN());
        BigInteger[] signature = signECDSAsecp256k1(msg, privKey);

        System.out.println("Message: " + msg);
        System.out.println("Private key: " + privKey.toString(16));
        System.out.println("Signature: r=" + signature[0].toString(16) + ", s=" + signature[1].toString(16));
    }
}