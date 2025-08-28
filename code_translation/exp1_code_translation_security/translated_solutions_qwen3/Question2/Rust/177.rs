pub fn sign(m: &[u8], sk: &SecretKey) -> Vec<u8> {
    sk.sign(m)
}