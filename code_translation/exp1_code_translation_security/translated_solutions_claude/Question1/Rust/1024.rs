use aes::Aes128;
use cfb_mode::{Cfb, Decryptor, Encryptor};
use cfb_mode::cipher::{NewCipher, StreamCipher};

type AesCfbEnc = Encryptor<Aes128>;
type AesCfbDec = Decryptor<Aes128>;

fn encrypt_string(message: &str, cipher: &mut AesCfbEnc) -> Vec<u8> {
    let mut buffer = message.as_bytes().to_vec();
    cipher.encrypt(&mut buffer);
    buffer
}

fn decrypt_string(ciphertext: &[u8], cipher: &mut AesCfbDec) -> Vec<u8> {
    let mut buffer = ciphertext.to_vec();
    cipher.decrypt(&mut buffer);
    buffer
}

fn main() {
    let key = b"This is a key123";
    let iv = b"This is an IV456";
    
    let mut encryptor = AesCfbEnc::new_from_slices(key, iv).unwrap();
    let mut decryptor = AesCfbDec::new_from_slices(key, iv).unwrap();
    
    let message = "The answer is no";
    let enc = encrypt_string(message, &mut encryptor);
    let dec = decrypt_string(&enc, &mut decryptor);
    
    // Convert decrypted bytes back to string for verification
    let decrypted_message = String::from_utf8(dec).unwrap();
    println!("Original: {}", message);
    println!("Encrypted: {:?}", enc);
    println!("Decrypted: {}", decrypted_message);
}