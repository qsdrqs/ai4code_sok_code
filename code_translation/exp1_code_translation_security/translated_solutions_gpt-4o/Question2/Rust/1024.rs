use k256::ecdsa::{SigningKey, Signature, signature::Signer};
use rand_core::OsRng;

fn generate_ecdsa_signed_msg(message: &str, private_key: &SigningKey) -> Signature {
    private_key.sign(message.as_bytes())
}

fn main() {
    // Generate Keys
    let private_key = SigningKey::random(&mut OsRng);
    let public_key = private_key.verifying_key();

    let message = "My test message";
    let signature = generate_ecdsa_signed_msg(message, &private_key);

    println!("{:?}", signature);
}