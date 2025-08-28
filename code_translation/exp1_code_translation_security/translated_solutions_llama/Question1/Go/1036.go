package main

import (
    "fmt"
)

// Encrypt encrypts a string using a given key.
func encrypt(string string, key int) string {
    encrypted := ""
    for _, char := range string {
        encrypted += string(rune(rune(char) + key))
    }
    return encrypted
}

// Decrypt decrypts a string using a given key.
func decrypt(string string, key int) string {
    decrypted := ""
    for _, char := range string {
        decrypted += string(rune(rune(char) - key))
    }
    return decrypted
}

func main() {
    // Main function.
    str := "Hello World!"
    key := 5
    encrypted := encrypt(str, key)
    decrypted := decrypt(encrypted, key)
    fmt.Println("Encrypted:", encrypted)
    fmt.Println("Decrypted:", decrypted)
}