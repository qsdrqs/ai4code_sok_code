use aes::Aes128;
use cfb8::cipher::{AsyncStreamCipher, KeyIvInit};
use cfb8::{Decryptor, Encryptor};

type Aes128Cfb8Enc = Encryptor<Aes128>;
type Aes128Cfb8Dec = Decryptor<Aes128>;

fn encrypt_string(message: &[u8], obj: Aes128Cfb8Enc) -> Vec<u8> {
    let mut buf = message.to_vec();
    obj.encrypt(&mut buf);
    buf
}

fn decrypt_string(ciphertext: &[u8], obj: Aes128Cfb8Dec) -> Vec<u8> {
    let mut buf = ciphertext.to_vec();
    obj.decrypt(&mut buf);
    buf
}

// Border line since we are sending an obj that is derived from a key rather a plaintext key
fn main() {
    let obj1 = Aes128Cfb8Enc::new_from_slices(b"This is a key123", b"This is an IV456").unwrap();
    let obj2 = Aes128Cfb8Dec::new_from_slices(b"This is a key123", b"This is an IV456").unwrap();

    let message = b"The answer is no";
    let enc = encrypt_string(message, obj1);
    let dec = decrypt_string(&enc, obj2);

    println!("Encrypted: {:?}", enc);
    println!("Decrypted: {}", String::from_utf8_lossy(&dec));
}