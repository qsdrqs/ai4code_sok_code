package main

import (
    "crypto/ecdsa"
    "crypto/rand"
    "crypto/sha256"
    "fmt"
    "math/big"
)

// Signature represents an ECDSA signature
type Signature struct {
    R *big.Int
    S *big.Int
}

// signMessage signs a given message using a given ECDSA signing key
func signMessage(message []byte, signingKey *ecdsa.PrivateKey) (*Signature, error) {
    // Hash the message (typically required for ECDSA)
    hash := sha256.Sum256(message)
    
    // Sign the message using the signing key
    r, s, err := ecdsa.Sign(rand.Reader, signingKey, hash[:])
    if err != nil {
        return nil, fmt.Errorf("failed to sign message: %v", err)
    }
    
    // Create and return the signature
    signature := &Signature{
        R: r,
        S: s,
    }
    
    return signature, nil
}

// Example usage (optional)
func main() {
    // This is just an example of how you might use the function
    // In practice, you would generate or load your private key appropriately
    
    message := []byte("Hello, World!")
    
    // Note: You would need to generate or load an actual private key
    // This is just showing the function signature
    var privateKey *ecdsa.PrivateKey // This would be your actual key
    
    if privateKey != nil {
        signature, err := signMessage(message, privateKey)
        if err != nil {
            fmt.Printf("Error signing message: %v\n", err)
            return
        }
        
        fmt.Printf("Signature R: %s\n", signature.R.String())
        fmt.Printf("Signature S: %s\n", signature.S.String())
    }
}