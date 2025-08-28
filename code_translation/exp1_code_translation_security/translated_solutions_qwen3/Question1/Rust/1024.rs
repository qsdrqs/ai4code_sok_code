use aes::Aes128;
use stream_ciphers::Cfb;

// A struct to encapsulate the AES-CFB configuration
struct AESCfb {
    key: [u8; 16],
    iv: [u8; 16],
}

impl AESCfb {
    // Create a new AES-CFB instance with the given key and IV
    fn new(key: [u8; 16], iv: [u8; 16]) -> Self {
        AESCfb { key, iv }
    }

    // Encrypt a string using AES-CFB with 8-bit segment size
    fn encrypt(&self, message: &str) -> Vec<u8> {
        let cipher = Aes128::new(&self.key);
        let mut cfb = Cfb::new(cipher, &self.iv, 1); // segment size = 1 byte (8 bits)
        let mut data = message.as_bytes().to_vec();
        cfb.apply_keystream(&mut data);
        data
    }

    // Decrypt a byte slice using AES-CFB with 8-bit segment size
    fn decrypt(&self, ciphertext: &[u8]) -> Vec<u8> {
        let cipher = Aes128::new(&self.key);
        let mut cfb = Cfb::new(cipher, &self.iv, 1); // segment size = 1 byte (8 bits)
        let mut data = ciphertext.to_vec();
        cfb.apply_keystream(&mut data);
        data
    }
}

fn main() {
    // Define the key and IV as byte arrays
    let key = *b"This is a key123"; // 16 bytes
    let iv = *b"This is an IV456"; // 16 bytes

    // Create two separate AES-CFB objects with the same key and IV
    let obj1 = AESCfb::new(key, iv);
    let obj2 = AESCfb::new(key, iv);

    // Encrypt and decrypt the message
    let message = "The answer is no";
    let enc = obj1.encrypt(message);
    let dec = obj2.decrypt(&enc);

    // Optional: Convert decrypted bytes back to string
    if let Ok(dec_str) = std::str::from_utf8(&dec) {
        println!("Decrypted: {}", dec_str);
    } else {
        println!("Failed to decode decrypted bytes as UTF-8");
    }
}