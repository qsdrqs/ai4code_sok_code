import com.google.gson.Gson;
import com.starkbank.ellipticcurve.Ecdsa;
import com.starkbank.ellipticcurve.PrivateKey;
import com.starkbank.ellipticcurve.PublicKey;
import com.starkbank.ellipticcurve.Signature;

public class EcdsaExample {
    
    public static Signature generateEcdsaSignedMsg(String message, PrivateKey privateKey) {
        return Ecdsa.sign(message, privateKey);
    }
    
    public static void main(String[] args) {
        // Generate Keys
        PrivateKey privateKey = new PrivateKey();
        PublicKey publicKey = privateKey.publicKey();
        
        String message = "My test message";
        System.out.println(generateEcdsaSignedMsg(message, privateKey));
    }
}