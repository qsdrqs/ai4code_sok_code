fn main() {
    let message = b"Hello, World!";
    // Example 32-byte private key (replace with your actual key)
    let private_key = [0x42u8; 32];

    match sign_message(message, &private_key) {
        Ok(sig) => {
            print!("Signature: ");
            for byte in &sig {
                print!("{:02x}", byte);
            }
            println!();
        }
        Err(e) => eprintln!("Error signing message: {}", e),
    }
}