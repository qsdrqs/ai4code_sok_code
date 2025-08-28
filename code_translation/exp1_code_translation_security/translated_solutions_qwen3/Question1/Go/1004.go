package main

import "fmt"

// encrypt performs XOR encryption on the given message using the provided key.
func encrypt(message, key string) string {
    encrypted := ""
    keyRunes := []rune(key)
    messageRunes := []rune(message)
    for i := 0; i < len(messageRunes); i++ {
        encrypted += string(messageRunes[i] ^ keyRunes[i % len(keyRunes)])
    }
    return encrypted
}

// decrypt uses the same XOR logic as encrypt to decrypt the message.
func decrypt(encrypted, key string) string {
    return encrypt(encrypted, key)
}

func main() {
    result := decrypt(encrypt("Hello world", "key"), "key")
    fmt.Println(result) // Expected output: "Hello world"
}