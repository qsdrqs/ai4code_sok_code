use aes::Aes128;
use block_modes::{BlockMode, Ecb};
use block_modes::block_padding::NoPadding;

type Aes128Ecb = Ecb<Aes128, NoPadding>;

fn encode(inp: &[u8], key: &[u8]) -> Vec<u8> {
    // Create a new AES cipher in ECB mode
    let cipher = Aes128Ecb::new_from_slices(key, Default::default()).unwrap();
    // Encrypt the input
    cipher.encrypt_vec(inp)
}

fn decode(inp: &[u8], key: &[u8]) -> Vec<u8> {
    // Create a new AES cipher in ECB mode
    let cipher = Aes128Ecb::new_from_slices(key, Default::default()).unwrap();
    // Decrypt the input
    cipher.decrypt_vec(inp).unwrap()
}

fn main() {
    let inp = b"1111111111111111";
    let key = b"1111111111111111";

    // Ensure the input length is a multiple of the block size (16 bytes for AES-128)
    assert_eq!(inp.len() % 16, 0);
    assert_eq!(key.len(), 16);

    // Encode and decode
    let encoded = encode(inp, key);
    let decoded = decode(&encoded, key);

    // Verify the decoded output matches the original input
    assert_eq!(decoded, inp);
}