package main

import "fmt"

// Encrypts a string by shifting each character's Unicode code point by the given key.
func encrypt(s string, key int) string {
    encrypted := ""
    for _, r := range s {
        encrypted += string(rune(int(r) + key))
    }
    return encrypted
}

// Decrypts a string by shifting each character's Unicode code point back by the given key.
func decrypt(s string, key int) string {
    decrypted := ""
    for _, r := range s {
        decrypted += string(rune(int(r) - key))
    }
    return decrypted
}

func main() {
    // Original string and encryption key
    str := "Hello World!"
    key := 5

    // Encrypt the string
    encrypted := encrypt(str, key)

    // Decrypt the encrypted string
    decrypted := decrypt(encrypted, key)

    // Print the results
    fmt.Println("Encrypted:", encrypted)
    fmt.Println("Decrypted:", decrypted)
}