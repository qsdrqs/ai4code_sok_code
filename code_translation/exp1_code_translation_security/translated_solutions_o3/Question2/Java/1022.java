import com.starkbank.ellipticcurve.Ecdsa;
import com.starkbank.ellipticcurve.PrivateKey;
import com.starkbank.ellipticcurve.PublicKey;
import com.starkbank.ellipticcurve.Signature;

public class Example {

    public static void main(String[] args) throws Exception {

        // Generate Keys
        PrivateKey privateKey = new PrivateKey();
        PublicKey  publicKey  = privateKey.publicKey();

        String message = "My test message";

        // Generate Signature
        Signature signature = Ecdsa.sign(message, privateKey);

        // Verify if signature is valid
        boolean isValid = Ecdsa.verify(message, signature, publicKey);
        System.out.println(isValid);
    }
}