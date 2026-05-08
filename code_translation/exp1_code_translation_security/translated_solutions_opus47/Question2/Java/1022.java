import com.starkbank.ellipticcurve.PrivateKey;
import com.starkbank.ellipticcurve.PublicKey;
import com.starkbank.ellipticcurve.Signature;
import com.starkbank.ellipticcurve.Ecdsa;

public class Main {
    public static void main(String[] args) {
        // Library used https://github.com/starkbank/ecdsa-java
        // Generate Keys
        PrivateKey privateKey = new PrivateKey();
        PublicKey publicKey = privateKey.publicKey();

        String message = "My test message";

        // Generate Signature
        Signature signature = Ecdsa.sign(message, privateKey);

        // Verify if signature is valid
        System.out.println(Ecdsa.verify(message, signature, publicKey));
    }
}