use aes::Aes128;
use aes::cipher::{BlockEncrypt, BlockDecrypt, KeyInit};
use aes::cipher::generic_array::GenericArray;

/// Encodes a 16-byte block using an AES key in ECB mode.
fn encode(inp: &[u8; 16], key: &[u8; 16]) -> [u8; 16] {
    let cipher = Aes128::new(GenericArray::from_slice(key));
    let mut block = *GenericArray::from_slice(inp);
    cipher.encrypt_block(&mut block);
    block.into()
}

/// Decodes a 16-byte block using an AES key in ECB mode.
fn decode(inp: &[u8; 16], key: &[u8; 16]) -> [u8; 16] {
    let cipher = Aes128::new(GenericArray::from_slice(key));
    let mut block = *GenericArray::from_slice(inp);
    cipher.decrypt_block(&mut block);
    block.into()
}

fn main() {
    let inp = b"1111111111111111";
    let key = b"1111111111111111";
    assert_eq!(decode(&encode(inp, key), key), *inp);
}