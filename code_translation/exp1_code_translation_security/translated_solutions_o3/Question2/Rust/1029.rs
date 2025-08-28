// --------  Dependencies  -------------------------------------------------
// p384      = "0.13"      (NIST-P384 / secp384r1 curve support)
// rand_core = "0.6"       (random-number source used by `p384`)
// -------------------------------------------------------------------------

use p384::ecdsa::{
    signature::{Signer, Verifier},   // common `sign()` / `verify()` traits
    Signature,                       // concrete signature type
    SigningKey, VerifyingKey,
};
use rand_core::OsRng;                // secure RNG supplied by the OS

/*  Equivalent to:

    def ecdsa_key(message, key):
        sk = SigningKey.generate(curve=NIST384p)
        vk = sk.get_verifying_key()
        signature = sk.sign(message)
        print(vk.verify(signature, message))
        return signature
*/
fn ecdsa_key(message: &[u8]) -> Signature {
    let mut rng = OsRng;
    let sk = SigningKey::random(&mut rng);
    let vk = sk.verifying_key();

    let signature = sk.sign(message);
    println!("{}", vk.verify(message, &signature).is_ok());

    signature
}

/*  def ecdsa_verify(signature, message, vk): */
fn ecdsa_verify(signature: &Signature, message: &[u8], vk: &VerifyingKey) -> bool {
    vk.verify(message, signature).is_ok()
}

/*  def ecdsa_genkey():  */
fn ecdsa_genkey() -> (SigningKey, VerifyingKey) {
    let mut rng = OsRng;
    let sk = SigningKey::random(&mut rng);
    let vk = sk.verifying_key();
    (sk, vk)
}

/*  def ecdsa_sign(sk, message):  */
fn ecdsa_sign(sk: &SigningKey, message: &[u8]) -> Signature {
    sk.sign(message)
}

/*  def ecdsa_verify_key(vk, signature, message):  */
fn ecdsa_verify_key(vk: &VerifyingKey, signature: &Signature, message: &[u8]) -> bool {
    vk.verify(message, signature).is_ok()
}

/*  Corresponds to the Python  `if __name__ == "__main__":` block  */
fn main() {
    let message = b"This is a test message";

    // Generate a fresh key-pair -------------------------------------------
    let (sk, vk) = ecdsa_genkey();

    // Show the raw bytes of the private and public keys -------------------
    // (purely for demonstration/debugging, never print secret keys in real
    //  applications!)
    println!("{:02x?}", sk.to_bytes());                        // 48-byte secret scalar
    println!("{:02x?}", vk.to_encoded_point(false).as_bytes()); // uncompressed point

    // Sign the message -----------------------------------------------------
    let signature = ecdsa_sign(&sk, message);
    println!("{:02x?}", signature.as_ref());                   // DER-encoded signature

    // Verify the signature -------------------------------------------------
    println!("{}", ecdsa_verify_key(&vk, &signature, message));
}