int main() {
    // 32-byte Fernet key (base64url decoded)
    unsigned char key[32] = {/* your 32-byte key */};
    
    const char* message = "Hello, World!";
    int encrypted_len, decrypted_len;
    
    // Encrypt
    unsigned char* encrypted = encrypt((unsigned char*)message, strlen(message), key, &encrypted_len);
    
    // Decrypt
    unsigned char* decrypted = decrypt(encrypted, encrypted_len, key, &decrypted_len);
    
    // Clean up
    free(encrypted);
    free(decrypted);
    
    return 0;
}