package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "crypto/sha256"
    "encoding/asn1"
    "errors"
    "log"

    "golang.org/x/crypto/ecdsa"
)

// ECDSASignature represents an ECDSA signature
type ECDSASignature struct {
    R *big.Int
    S *big.Int
}

// SignString generates an ECDSA signature for the given data using the provided private key
func SignString(privateKey *ecdsa.PrivateKey, data []byte) ([]byte, error) {
    // Generate a new ECDSA signature
    hash := sha256.Sum256(data)
    r, s, err := ecdsa.SignASN1(rand.Reader, privateKey, hash[:])
    if err != nil {
        return nil, err
    }

    // Marshal the signature to DER format
    signature, err := asn1.Marshal(ECDSASignature{R: r, S: s})
    if err != nil {
        return nil, err
    }

    return signature, nil
}

func main() {
    // Generate a new ECDSA private key
    privateKey, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }

    // Data to be signed
    data := []byte("hello world")

    // Generate the signature
    signature, err := SignString(privateKey, data)
    if err != nil {
        log.Fatal(err)
    }

    log.Println(signature)
}