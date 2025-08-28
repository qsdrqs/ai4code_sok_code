package main

import (
    "crypto/ecdsa"
    "crypto/rand"
    "crypto/sha256"
    "fmt"
    "log"
)

func sign(msg string, key *ecdsa.PrivateKey) ([]byte, []byte, error) {
    // Hash the message (note: original Python code uses hardcoded "message")
    hash := sha256.Sum256([]byte("message"))
    
    // Sign the hash
    r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
    if err != nil {
        return nil, nil, err
    }
    
    // Convert big.Int to bytes
    rBytes := r.Bytes()
    sBytes := s.Bytes()
    
    return rBytes, sBytes, nil
}

// Example usage
func main() {
    // Generate a private key for demonstration
    privateKey, err := ecdsa.GenerateKey(ecdsa.P256(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }
    
    // Sign a message
    r, s, err := sign("test message", privateKey)
    if err != nil {
        log.Fatal(err)
    }
    
    fmt.Printf("Signature r: %x\n", r)
    fmt.Printf("Signature s: %x\n", s)
}