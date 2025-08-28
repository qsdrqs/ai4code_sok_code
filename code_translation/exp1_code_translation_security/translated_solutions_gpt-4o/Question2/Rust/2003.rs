use k256::ecdsa::{SigningKey, signature::Signer};

fn sign(inp: &str, key: &SigningKey) -> Vec<u8> {
    key.sign(inp.as_bytes()).as_ref().to_vec()
}

fn main() {
    // Generate a new signing key
    let signing_key = SigningKey::random(&mut rand::thread_rng());

    // Sign the message
    let signature = sign("Hello world", &signing_key);

    // Print the signature
    println!("{:?}", signature);
}