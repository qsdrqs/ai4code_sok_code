package main

import (
    "crypto/ed25519"
    "fmt"
)

func signMessage(message []byte, signingKey ed25519.PrivateKey) []byte {
    return ed25519.Sign(signingKey, message)
}

// Example usage
func main() {
    // Generate a key pair for demonstration
    publicKey, privateKey, err := ed25519.GenerateKey(nil)
    if err != nil {
        panic(err)
    }
    
    message := []byte("Hello, World!")
    signature := signMessage(message, privateKey)
    
    fmt.Printf("Message: %s\n", message)
    fmt.Printf("Signature: %x\n", signature)
    
    // Verify the signature
    valid := ed25519.Verify(publicKey, message, signature)
    fmt.Printf("Signature valid: %t\n", valid)
}