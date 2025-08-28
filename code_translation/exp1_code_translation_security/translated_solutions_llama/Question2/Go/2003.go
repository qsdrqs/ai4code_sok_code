package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/hex"
    "fmt"
    "log"
)

func sign(inp string, key *ecdsa.PrivateKey) ([]byte, error) {
    // Hash the input data
    hash := []byte(inp)

    // Sign the hashed data
    r, s, err := ecdsa.SignASN1(rand.Reader, key, hash)
    if err != nil {
        return nil, err
    }

    // Combine r and s into a single byte slice
    signature := append(r.Bytes(), s.Bytes()...)
    return signature, nil
}

func main() {
    // Generate a new ECDSA private key
    sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }

    // Sign the input data
    inp := "Hello world"
    signature, err := sign(inp, sk)
    if err != nil {
        log.Fatal(err)
    }

    // Print the signature as a hexadecimal string
    fmt.Println(hex.EncodeToString(signature))
}