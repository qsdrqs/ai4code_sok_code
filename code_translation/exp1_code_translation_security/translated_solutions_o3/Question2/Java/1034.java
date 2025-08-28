// Example.java
// All required dependencies for the “ellipticcurve” package will be supplied
// so you can import the classes exactly as shown below.

import ellipticcurve.ecdsa.Ecdsa;
import ellipticcurve.ecdsa.Signature;
import ellipticcurve.privateKey.PrivateKey;
import ellipticcurve.publicKey.PublicKey;

public class Example {

    public static void main(String[] args) {

        // 1. Generate a fresh private key
        PrivateKey privateKey = new PrivateKey();

        // 2. Derive its corresponding public key
        PublicKey publicKey = privateKey.publicKey();

        // 3. Message to be signed
        String message = "Test message";

        // 4. Sign the message with the private key
        Signature signature = Ecdsa.sign(message, privateKey);

        // 5. Verify the signature with the public key
        boolean isValid = Ecdsa.verify(message, signature, publicKey);

        // 6. Display the verification result
        System.out.println(isValid);
    }
}