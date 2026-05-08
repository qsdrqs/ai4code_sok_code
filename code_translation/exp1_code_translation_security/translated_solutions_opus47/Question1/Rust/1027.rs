use aes::cipher::{
    block_padding::Pkcs7,
    generic_array::GenericArray,
    BlockDecryptMut, BlockEncryptMut, KeyIvInit,
};
use aes::Aes128;

type Aes128CbcEnc = cbc::Encryptor<Aes128>;
type Aes128CbcDec = cbc::Decryptor<Aes128>;

fn encrypt(message: &str, key: &[u8], iv: &[u8]) -> Vec<u8> {
    // convert message to bytes
    let message_bytes = message.as_bytes();
    // create cipher
    let cipher = Aes128CbcEnc::new(
        GenericArray::from_slice(key),
        GenericArray::from_slice(iv),
    );
    // encrypt message (PKCS7 padding applied automatically)
    let ciphertext = cipher.encrypt_padded_vec_mut::<Pkcs7>(message_bytes);
    // return ciphertext
    ciphertext
}

fn decrypt(ciphertext: &[u8], key: &[u8], iv: &[u8]) -> Result<String, Box<dyn std::error::Error>> {
    // create cipher
    let cipher = Aes128CbcDec::new(
        GenericArray::from_slice(key),
        GenericArray::from_slice(iv),
    );
    // decrypt message (PKCS7 padding removed automatically)
    let message_bytes = cipher.decrypt_padded_vec_mut::<Pkcs7>(ciphertext)?;
    // convert message to string
    let message = String::from_utf8(message_bytes)?;
    // return message
    Ok(message)
}