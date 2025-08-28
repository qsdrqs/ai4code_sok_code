package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/hex"
    "fmt"
    "log"
)

// sign generates a signature for a given message using the provided private key.
func sign(msg []byte, key *ecdsa.PrivateKey) ([]byte, error) {
    // Generate signature
    r, s, err := ecdsa.SignASN1(rand.Reader, &key.PublicKey, msg)
    if err != nil {
        return nil, err
    }

    // Marshal the signature
    signature := append(r.Bytes(), s.Bytes()...)
    return signature, nil
}

// verify checks if a signature is valid for a given message and public key.
func verify(msg []byte, signature []byte, key *ecdsa.PublicKey) bool {
    // Unmarshal the signature
    if len(signature) != 64 {
        return false
    }
    r := new(big.Int).SetBytes(signature[:32])
    s := new(big.Int).SetBytes(signature[32:])

    // Verify the signature
    return ecdsa.Verify(key, msg, r, s)
}

func main() {
    // Generate a new ECDSA private key
    sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }

    // Sign a message
    msg := []byte("message")
    signature, err := sign(msg, sk)
    if err != nil {
        log.Fatal(err)
    }

    // Verify the signature
    valid := verify(msg, signature, &sk.PublicKey)
    if !valid {
        log.Fatal("Invalid signature")
    }

    // Print the signature
    fmt.Println(hex.EncodeToString(signature))
}