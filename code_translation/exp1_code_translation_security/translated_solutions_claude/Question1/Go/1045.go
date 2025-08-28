package main

import (
    "fmt"
    "strings"
)

// function to encrypt a given string
func encrypt(text string, key int) string {
    var result strings.Builder
    
    for _, char := range text {
        encryptedChar := rune(int(char) + key)
        result.WriteRune(encryptedChar)
    }
    
    return result.String()
}

// function to decrypt a given string
func decrypt(text string, key int) string {
    var result strings.Builder
    
    for _, char := range text {
        decryptedChar := rune(int(char) - key)
        result.WriteRune(decryptedChar)
    }
    
    return result.String()
}

func main() {
    fmt.Println(encrypt("name", 1234))
    fmt.Println(decrypt("ՀԳԿԷ", 1234))
}