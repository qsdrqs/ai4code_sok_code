use k256::ecdsa::{SigningKey, Signature};
use sha2::{Digest, Sha256};
use hex;

// Helper function to encode a big-endian byte slice as a DER-encoded INTEGER
fn encode_der_integer(bytes: &[u8]) -> Vec<u8> {
    let first_non_zero = match bytes.iter().position(|&b| b != 0) {
        Some(pos) => pos,
        None => return vec![0x02, 0x01, 0x00], // Zero
    };

    let mut data = bytes[first_non_zero..].to_vec();

    if (data[0] & 0x80) != 0 {
        data.insert(0, 0x00); // Prepend zero if high bit is set
    }

    let mut encoded = Vec::with_capacity(2 + data.len());
    encoded.push(0x02); // INTEGER tag
    encoded.push(data.len() as u8);
    encoded.extend_from_slice(&data);

    encoded
}

fn sign_message(message: &str, private_key: &[u8]) -> String {
    // Step 1: Hash the message using SHA-256
    let message_hash = Sha256::digest(message.as_bytes());
    let message_hash_bytes: [u8; 32] = message_hash.as_slice().try_into().unwrap();

    // Step 2: Create the signing key from the private key bytes
    let signing_key = SigningKey::from_bytes(private_key).expect("Invalid private key");

    // Step 3: Sign the precomputed hash
    let signature = signing_key.sign_prehash(&message_hash_bytes);

    // Step 4: Split the signature into r and s
    let (r, s) = signature.split_bytes32();

    // Step 5: Encode r and s in DER format
    let encoded_r = encode_der_integer(&r);
    let encoded_s = encode_der_integer(&s);

    // Step 6: Construct the DER-encoded signature
    let total_length = encoded_r.len() + encoded_s.len();
    let mut der_signature = Vec::with_capacity(2 + 1 + total_length);
    der_signature.push(0x30); // SEQUENCE tag
    der_signature.push(total_length as u8);
    der_signature.extend_from_slice(&encoded_r);
    der_signature.extend_from_slice(&encoded_s);

    // Step 7: Convert the DER signature to a hex string
    hex::encode(der_signature)
}

fn main() {
    // Test case
    let private_key = hex::decode("e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109")
        .expect("Invalid hex in private key");

    let message = "Hello";
    let signature = sign_message(message, &private_key);
    println!("{}", signature);
}