package main

import (
    "fmt"
)

// encrypt encrypts a given string using a given symmetric key.
func encrypt(str string, key string) string {
    var encryptedString string
    for i := 0; i < len(str); i++ {
        encryptedString += string(rune(str[i]) ^ rune(key[i%len(key)]))
    }
    return encryptedString
}

// decrypt decrypts a given string using a given symmetric key.
func decrypt(str string, key string) string {
    var decryptedString string
    for i := 0; i < len(str); i++ {
        decryptedString += string(rune(str[i]) ^ rune(key[i%len(key)]))
    }
    return decryptedString
}

// Example usage
func main() {
    originalText := "Hello, World!"
    key := "mykey"
    
    encrypted := encrypt(originalText, key)
    fmt.Printf("Encrypted: %s\n", encrypted)
    
    decrypted := decrypt(encrypted, key)
    fmt.Printf("Decrypted: %s\n", decrypted)
}