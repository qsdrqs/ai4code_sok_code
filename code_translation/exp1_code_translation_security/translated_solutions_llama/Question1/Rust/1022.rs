fn encrypt(inp: &str, key: &[u8]) -> Result<Vec<u8>, fernet::Error> {
    let fernet = Fernet::new(key)?;
    Ok(fernet.encrypt(inp.as_bytes())?)
}

fn decrypt(inp: &[u8], key: &[u8]) -> Result<String, fernet::Error> {
    let fernet = Fernet::new(key)?;
    let decrypted = fernet.decrypt(inp)?;
    Ok(String::from_utf8(decrypted)?)
}