import java.security.PrivateKey;
import java.security.Signature;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.SignatureException;

public static byte[] signusingECDSA(PrivateKey key, byte[] message) 
    throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
    
    Signature signer = Signature.getInstance("SHA256withECDSA");
    signer.initSign(key);
    signer.update(message);
    return signer.sign();
}