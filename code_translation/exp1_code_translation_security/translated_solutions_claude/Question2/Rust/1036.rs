use sha2::{Sha256, Digest};
use secp256k1::{Secp256k1, SecretKey, Message};
use hex;

fn sign_message(message: &str, private_key: &[u8]) -> Result<String, Box<dyn std::error::Error>> {
    // Hash the message
    let mut hasher = Sha256::new();
    hasher.update(message.as_bytes());
    let message_hash = hasher.finalize();

    // Create secp256k1 context
    let secp = Secp256k1::new();
    
    // Create secret key from private key bytes
    let secret_key = SecretKey::from_slice(private_key)?;
    
    // Create message from hash
    let msg = Message::from_slice(&message_hash)?;
    
    // Sign the message
    let signature = secp.sign_ecdsa(&msg, &secret_key);
    
    // Serialize signature to DER format
    let der_signature = signature.serialize_der();
    
    // Return the signature as hex string
    Ok(hex::encode(der_signature))
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    // Test the function
    let private_key = hex::decode("e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109")?;
    let message = "Hello";
    let signature = sign_message(message, &private_key)?;
    println!("{}", signature);
    
    Ok(())
}