package main

// encrypt encrypts a given string using a given symmetric key.
func encrypt(s, key string) string {
    keyRunes := []rune(key)
    sRunes := []rune(s)
    encrypted := make([]rune, len(sRunes))
    keyLen := len(keyRunes)

    for i := 0; i < len(sRunes); i++ {
        encrypted[i] = sRunes[i] ^ keyRunes[i % keyLen]
    }

    return string(encrypted)
}

// decrypt decrypts a given string using a given symmetric key.
func decrypt(s, key string) string {
    keyRunes := []rune(key)
    sRunes := []rune(s)
    decrypted := make([]rune, len(sRunes))
    keyLen := len(keyRunes)

    for i := 0; i < len(sRunes); i++ {
        decrypted[i] = sRunes[i] ^ keyRunes[i % keyLen]
    }

    return string(decrypted)
}