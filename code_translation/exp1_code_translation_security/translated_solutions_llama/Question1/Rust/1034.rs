use aes_gcm::{Aes256Gcm, Key, Nonce};
use base64;
use rand::Rng;
use serde::{Serialize, Deserialize};

#[derive(Serialize, Deserialize)]
struct EncodedData {
    nonce: Vec<u8>,
    ciphertext: Vec<u8>,
}

fn encode_str(plain_text: &str, key: &str) -> String {
    // Generate a random 96-bit IV.
    let mut rng = rand::thread_rng();
    let nonce = rng.gen::<[u8; 12]>();

    // Create a new AES-GCM instance.
    let key_bytes = key.as_bytes();
    let key = Key::from_slice(&key_bytes[..32]);
    let cipher = Aes256Gcm::new(key);

    // Encrypt the plaintext.
    let encrypted = cipher.encrypt(&nonce.into(), plain_text.as_bytes()).unwrap();

    // Serialize the nonce and ciphertext.
    let encoded_data = EncodedData {
        nonce: nonce.to_vec(),
        ciphertext: encrypted,
    };

    // Base64 encode the serialized data.
    base64::encode(&bincode::serialize(&encoded_data).unwrap())
}

fn decode_str(str_encoded: &str, key: &str) -> String {
    // Base64 decode the input string.
    let decoded_bytes = base64::decode(str_encoded).unwrap();

    // Deserialize the nonce and ciphertext.
    let encoded_data: EncodedData = bincode::deserialize(&decoded_bytes).unwrap();

    // Create a new AES-GCM instance.
    let key_bytes = key.as_bytes();
    let key = Key::from_slice(&key_bytes[..32]);
    let cipher = Aes256Gcm::new(key);

    // Decrypt the ciphertext.
    let decrypted = cipher.decrypt(&encoded_data.nonce.into(), &encoded_data.ciphertext).unwrap();

    // Return the decrypted string.
    String::from_utf8(decrypted).unwrap()
}

fn main() {
    let str_encoded1 = encode_str("I am okay", "wowwowwowwowwowwowwowwow");
    println!("{}", decode_str(&str_encoded1, "wowwowwowwowwowwowwowwow"));
}