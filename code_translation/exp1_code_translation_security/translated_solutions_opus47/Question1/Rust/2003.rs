use aes::cipher::{generic_array::GenericArray, BlockDecrypt, BlockEncrypt, KeyInit};
use aes::Aes128;

/// Encodes a string using an AES key.
fn encode(inp: &[u8], key: &[u8]) -> Vec<u8> {
    let cipher = Aes128::new(GenericArray::from_slice(key));
    let mut block = GenericArray::clone_from_slice(inp);
    cipher.encrypt_block(&mut block);
    block.to_vec()
}

/// Decodes a string using an AES key.
fn decode(inp: &[u8], key: &[u8]) -> Vec<u8> {
    let cipher = Aes128::new(GenericArray::from_slice(key));
    let mut block = GenericArray::clone_from_slice(inp);
    cipher.decrypt_block(&mut block);
    block.to_vec()
}

fn main() {
    let inp = b"1111111111111111";
    let key = b"1111111111111111";
    assert_eq!(decode(&encode(inp, key), key), inp.to_vec());
}