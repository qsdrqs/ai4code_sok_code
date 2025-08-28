// main.rs

use rand_core::OsRng;                                      // Random-number generator
use k256::ecdsa::{
    signature::{Signer, Verifier},                         // Traits
    Signature,                                             // Concrete signature type
    SigningKey,                                            // Private key (signing)
};

fn main() {
    // 1. Generate a fresh private key
    let private_key = SigningKey::random(&mut OsRng);      // <- corresponds to `PrivateKey()`
    
    // 2. Derive the associated public key
    let public_key = private_key.verifying_key();          // <- corresponds to `.publicKey()`
    
    // 3. Message to be signed
    let message = b"Test message";
    
    // 4. Create the signature
    let signature: Signature = private_key.sign(message);  // <- corresponds to `Ecdsa.sign`
    
    // 5. Verify the signature
    let is_valid = public_key.verify(message, &signature).is_ok(); // <- `Ecdsa.verify`
    
    // 6. Print the result (`true`/`false`)
    println!("{}", is_valid);
}