package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "crypto/sha256"
    "fmt"
    "math/big"
)

// ECDSA signature structure
type ECDSASignature struct {
    R, S *big.Int
}

func signMessage(message []byte, curve elliptic.Curve) (*ECDSASignature, error) {
    // Generate a random private key
    privateKey, err := ecdsa.GenerateKey(curve, rand.Reader)
    if err != nil {
        return nil, fmt.Errorf("failed to generate private key: %v", err)
    }
    
    // Get the public key (it's embedded in the private key struct)
    publicKey := &privateKey.PublicKey
    _ = publicKey // Public key is available if needed
    
    // Hash the message (ECDSA typically signs a hash, not the raw message)
    hash := sha256.Sum256(message)
    
    // Sign the message hash
    r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
    if err != nil {
        return nil, fmt.Errorf("failed to sign message: %v", err)
    }
    
    signature := &ECDSASignature{
        R: r,
        S: s,
    }
    
    return signature, nil
}

// Example usage
func main() {
    message := []byte("Hello, World!")
    
    // Sign with P256 curve (equivalent to secp256r1)
    signature, err := signMessage(message, elliptic.P256())
    if err != nil {
        fmt.Printf("Error signing message: %v\n", err)
        return
    }
    
    fmt.Printf("Signature R: %x\n", signature.R)
    fmt.Printf("Signature S: %x\n", signature.S)
}