package main

import "fmt"

// encrypt encrypts a string using a given key
func encrypt(str string, key int) string {
    encrypted := ""
    for i := 0; i < len(str); i++ {
        encrypted += string(rune(str[i]) + rune(key))
    }
    return encrypted
}

// decrypt decrypts a string using a given key
func decrypt(str string, key int) string {
    decrypted := ""
    for i := 0; i < len(str); i++ {
        decrypted += string(rune(str[i]) - rune(key))
    }
    return decrypted
}

// main is the main function
func main() {
    str := "Hello World!"
    key := 5
    encrypted := encrypt(str, key)
    decrypted := decrypt(encrypted, key)
    fmt.Println("Encrypted:", encrypted)
    fmt.Println("Decrypted:", decrypted)
}