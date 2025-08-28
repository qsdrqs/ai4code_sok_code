package main

import (
    "crypto"
    "crypto/rand"
    "crypto/rsa"
    "crypto/sha256"
    "crypto/x509"
    "encoding/pem"
    "fmt"
    "log"
)

// sign a message using RSA PKCS1v15 signature scheme
func sign(msg []byte, privKey *rsa.PrivateKey) ([]byte, error) {
    // Create SHA256 hash of the message
    hash := sha256.Sum256(msg)
    
    // Sign the hash using PKCS1v15
    signature, err := rsa.SignPKCS1v15(rand.Reader, privKey, crypto.SHA256, hash[:])
    if err != nil {
        return nil, err
    }
    
    return signature, nil
}

// Helper function to load RSA private key from PEM string
func loadPrivateKeyFromPEM(pemData string) (*rsa.PrivateKey, error) {
    block, _ := pem.Decode([]byte(pemData))
    if block == nil {
        return nil, fmt.Errorf("failed to decode PEM block")
    }
    
    privKey, err := x509.ParsePKCS1PrivateKey(block.Bytes)
    if err != nil {
        // Try PKCS8 format if PKCS1 fails
        key, err := x509.ParsePKCS8PrivateKey(block.Bytes)
        if err != nil {
            return nil, err
        }
        rsaKey, ok := key.(*rsa.PrivateKey)
        if !ok {
            return nil, fmt.Errorf("not an RSA private key")
        }
        return rsaKey, nil
    }
    
    return privKey, nil
}

// Example usage
func main() {
    // Generate a sample RSA key pair for demonstration
    privKey, err := rsa.GenerateKey(rand.Reader, 2048)
    if err != nil {
        log.Fatal(err)
    }
    
    message := []byte("Hello, World!")
    
    signature, err := sign(message, privKey)
    if err != nil {
        log.Fatal(err)
    }
    
    fmt.Printf("Message: %s\n", message)
    fmt.Printf("Signature length: %d bytes\n", len(signature))
    fmt.Printf("Signature (hex): %x\n", signature)
}