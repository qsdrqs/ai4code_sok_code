import com.starkbank.ellipticcurve.PrivateKey;
import com.starkbank.ellipticcurve.PublicKey;
import com.starkbank.ellipticcurve.Signature;
import com.starkbank.ellipticcurve.Ecdsa;

public class Main {
    public static void main(String[] args) {
        PrivateKey privateKey = new PrivateKey();
        PublicKey publicKey = privateKey.publicKey();

        String message = "Test message";

        Signature signature = Ecdsa.sign(message, privateKey);

        System.out.println(Ecdsa.verify(message, signature, publicKey));
    }
}