package main

import (
    "fmt"
    "strings"
)

// encrypt with key
func encrypt(key, plaintext string) string {
    var ciphertext []string
    
    for i := 0; i < len(plaintext); i++ {
        keyC := key[i % len(key)]
        cipherC := byte((int(plaintext[i]) + int(keyC)) % 256)
        ciphertext = append(ciphertext, string(cipherC))
    }
    
    return strings.Join(ciphertext, "")
}

// decrypt with key
func decrypt(key, ciphertext string) string {
    var plaintext []string
    
    for i := 0; i < len(ciphertext); i++ {
        keyC := key[i % len(key)]
        plainC := byte((256 + int(ciphertext[i]) - int(keyC)) % 256)
        plaintext = append(plaintext, string(plainC))
    }
    
    return strings.Join(plaintext, "")
}

func main() {
    fmt.Println(decrypt("123", encrypt("123", "helloworld")))
}