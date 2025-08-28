use secp256k1::{Context, Message, PrivateKey, PublicKey, Secp256k1, Signature};
use serde_json::json;

// Function to generate ECDSA signed message
fn generate_ecdsa_signed_msg(message: &str, private_key: &PrivateKey) -> String {
    let s = Secp256k1::new();
    let msg = Message::from_slice(message.as_bytes()).unwrap();
    let signature = s.sign(&msg, private_key).unwrap();

    // Serialize signature to JSON
    json!({
        "signature": format!("{:?}", signature),
        "message": message,
    })
    .to_string()
}

fn main() {
    // Generate Keys
    let s = Secp256k1::new();
    let private_key = PrivateKey::new(&s, &mut rand::thread_rng());
    let public_key = PublicKey::from_private_key(&s, &private_key);

    let message = "My test message";
    println!("{}", generate_ecdsa_signed_msg(message, &private_key));
}