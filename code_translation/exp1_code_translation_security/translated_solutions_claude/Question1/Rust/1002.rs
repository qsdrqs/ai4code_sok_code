use aes::cipher::{block_padding::Pkcs7, BlockDecryptMut, BlockEncryptMut, KeyIvInit};
use rand::RngCore;

type Aes256CbcEnc = cbc::Encryptor<aes::Aes256>;
type Aes256CbcDec = cbc::Decryptor<aes::Aes256>;

fn encrypt(data: &[u8], key: &[u8], iv: &[u8]) -> Vec<u8> {
    let cipher = Aes256CbcEnc::new_from_slices(key, iv).unwrap();
    cipher.encrypt_padded_vec_mut::<Pkcs7>(data)
}

fn decrypt(data: &[u8], key: &[u8], iv: &[u8]) -> Vec<u8> {
    let cipher = Aes256CbcDec::new_from_slices(key, iv).unwrap();
    cipher.decrypt_padded_vec_mut::<Pkcs7>(data).unwrap()
}

fn test() {
    let data = b"a secret message";
    let mut key = [0u8; 32];
    let mut iv = [0u8; 16];
    
    // Generate random key and IV
    rand::thread_rng().fill_bytes(&mut key);
    rand::thread_rng().fill_bytes(&mut iv);
    
    let encrypted = encrypt(data, &key, &iv);
    let decrypted = decrypt(&encrypted, &key, &iv);
    
    assert_eq!(decrypted, data);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_encryption_decryption() {
        test();
    }
}