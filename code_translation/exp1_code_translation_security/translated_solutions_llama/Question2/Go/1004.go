package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "crypto/sha256"
    "encoding/hex"
    "fmt"
    "log"
    "math/big"
)

func signMessage(message []byte, privateKeyBytes []byte) ([]byte, error) {
    // Parse the private key from bytes
    x, y := new(big.Int).SetBytes(privateKeyBytes[:32]), new(big.Int).SetBytes(privateKeyBytes[32:])
    privateKey := ecdsa.PrivateKey{
        PublicKey: ecdsa.PublicKey{
            Curve: elliptic.SECP256k1,
            X:     x,
            Y:     y,
        },
        D: new(big.Int).SetBytes(privateKeyBytes[32:]),
    }

    // Hash the message
    hash := sha256.Sum256(message)

    // Sign the hashed message
    r, s, err := ecdsa.Sign(rand.Reader, &privateKey, hash[:])
    if err != nil {
        return nil, err
    }

    // Convert the signature to DER format
    rBytes := r.Bytes()
    sBytes := s.Bytes()

    // Ensure r and s are 32 bytes long (256 bits)
    rBytes = padBytes(rBytes, 32)
    sBytes = padBytes(sBytes, 32)

    signature := []byte{0x30}
    signature = append(signature, 0x2, byte(len(rBytes)))
    signature = append(signature, rBytes...)
    signature = append(signature, 0x2, byte(len(sBytes)))
    signature = append(signature, sBytes...)

    return signature, nil
}

// Helper function to pad bytes to a specified length
func padBytes(b []byte, length int) []byte {
    padded := make([]byte, length)
    copy(padded[length-len(b):], b)
    return padded
}

func main() {
    message := []byte("hello")
    privateKeyBytes := append([]byte{}, []byte("qwertyuiopasdfghjklzxcvbnmqwerty")...)
    for len(privateKeyBytes) < 64 {
        privateKeyBytes = append(privateKeyBytes, 0)
    }

    signature, err := signMessage(message, privateKeyBytes)
    if err != nil {
        log.Fatal(err)
    }

    fmt.Println(hex.EncodeToString(signature))
}