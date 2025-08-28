package main

import "fmt"

// Encrypt encrypts the input string using a key.
func encrypt(key, s string) string {
    keyRunes := []rune(key)
    sRunes := []rune(s)
    encryptedRunes := make([]rune, len(sRunes))
    keyLen := len(keyRunes)

    for i := 0; i < len(sRunes); i++ {
        keyC := keyRunes[i%keyLen]
        encryptedRunes[i] = (sRunes[i] + keyC) % 256
    }

    return string(encryptedRunes)
}

// Decrypt decrypts the input string using a key.
func decrypt(key, s string) string {
    keyRunes := []rune(key)
    sRunes := []rune(s)
    decryptedRunes := make([]rune, len(sRunes))
    keyLen := len(keyRunes)

    for i := 0; i < len(sRunes); i++ {
        keyC := keyRunes[i%keyLen]
        decryptedC := (sRunes[i] - keyC) & 0xFF // Mask to simulate Python's mod behavior
        decryptedRunes[i] = decryptedC
    }

    return string(decryptedRunes)
}

// Example usage
func main() {
    key := "key"
    original := "Hello, World!"

    encrypted := encrypt(key, original)
    decrypted := decrypt(key, encrypted)

    fmt.Println("Original: ", original)
    fmt.Println("Encrypted: ", encrypted)
    fmt.Println("Decrypted: ", decrypted)
}