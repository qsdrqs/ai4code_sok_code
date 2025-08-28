package main

import "fmt"

// Encrypt function appends the key after each character in the message.
func encrypt(msg, key string) string {
    var temp string
    for _, c := range msg {
        temp += string(c) + key
    }
    return temp
}

// Decrypt function extracts every other character from the message,
// starting with the first character. The key is not used in this implementation.
func decrypt(msg string, _ string) string {
    var temp string
    var control int
    for _, c := range msg {
        if control%2 == 0 {
            temp += string(c)
        }
        control++
    }
    return temp
}

func main() {
    // Test the encrypt and decrypt functions
    fmt.Println(encrypt("a", "b")) // Output: ab
    fmt.Println(decrypt("ab", "b")) // Output: a
}