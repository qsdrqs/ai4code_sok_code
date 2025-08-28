use aes::Aes128;
use block_modes::BlockMode;
use block_modes::Ecb;
use generic_array::GenericArray;
use generic_array::typenum::U16;

/// Encrypts the input using AES in ECB mode.
fn encode(inp: &[u8], key: &[u8]) -> Vec<u8> {
    let key_arr = GenericArray::from_slice::<U16>(key);
    let cipher = Ecb::<Aes128>::new(key_arr);
    cipher.encrypt_vec(inp)
}

/// Decrypts the input using AES in ECB mode.
fn decode(inp: &[u8], key: &[u8]) -> Vec<u8> {
    let key_arr = GenericArray::from_slice::<U16>(key);
    let cipher = Ecb::<Aes128>::new(key_arr);
    cipher.decrypt_vec(inp)
}

fn main() {
    let inp = b"1111111111111111"; // 16-byte input
    let key = b"1111111111111111"; // 16-byte key

    // Test: encode then decode should return the original input
    assert_eq!(&decode(&encode(inp, key), key)[..], &inp[..]);

    println!("Test passed: decode(encode(...)) == input");
}