package main

import "fmt"

// encrypt performs XOR encryption on the input string using the provided symmetric key.
// Each character of the input string is XORed with the corresponding character of the key,
// cycling through the key if it is shorter than the string.
func encrypt(s string, key string) string {
    keyRunes := []rune(key)
    lenKey := len(keyRunes)
    sRunes := []rune(s)
    encrypted := make([]rune, len(sRunes))

    for i, r := range sRunes {
        encrypted[i] = r ^ keyRunes[i % lenKey]
    }

    return string(encrypted)
}

// decrypt performs XOR decryption on the input string using the provided symmetric key.
// Since XOR is its own inverse, this function is identical to encrypt.
func decrypt(s string, key string) string {
    keyRunes := []rune(key)
    lenKey := len(keyRunes)
    sRunes := []rune(s)
    decrypted := make([]rune, len(sRunes))

    for i, r := range sRunes {
        decrypted[i] = r ^ keyRunes[i % lenKey]
    }

    return string(decrypted)
}

// Example usage
func main() {
    original := "Hello, World!"
    key := "secret"

    encrypted := encrypt(original, key)
    decrypted := decrypt(encrypted, key)

    fmt.Println("Original: ", original)
    fmt.Println("Encrypted:", encrypted)
    fmt.Println("Decrypted:", decrypted)
}