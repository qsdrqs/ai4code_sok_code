package main

// encrypt encrypts a given string using a given symmetric key.
func encrypt(s, key string) string {
    encrypted := make([]byte, len(s))
    for i := 0; i < len(s); i++ {
        encrypted[i] = s[i] ^ key[i%len(key)]
    }
    return string(encrypted)
}

// decrypt decrypts a given string using a given symmetric key.
func decrypt(s, key string) string {
    decrypted := make([]byte, len(s))
    for i := 0; i < len(s); i++ {
        decrypted[i] = s[i] ^ key[i%len(key)]
    }
    return string(decrypted)
}