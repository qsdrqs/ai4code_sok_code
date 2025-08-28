package main

import "fmt"

// Encrypts a string by shifting each character's Unicode code point
// forward by the given key.
func encrypt(s string, key int) string {
    var newString string
    for _, c := range s {
        // Convert the integer key to rune to match the type of 'c'
        newString += string(c + rune(key))
    }
    return newString
}

// Decrypts a string by shifting each character's Unicode code point
// backward by the given key.
func decrypt(s string, key int) string {
    var newString string
    for _, c := range s {
        newString += string(c - rune(key))
    }
    return newString
}

func main() {
    // Test the encryption and decryption
    msg := encrypt("this is cool if it really works", 2)
    res := decrypt(msg, 2)

    // Print the encrypted and decrypted strings
    fmt.Println("Encrypted:", msg)
    fmt.Println("Decrypted:", res)
}