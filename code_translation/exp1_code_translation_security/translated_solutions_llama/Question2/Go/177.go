package main

import (
    "crypto/ecdsa"
    "crypto/rand"
    "crypto/x509"
    "encoding/asn1"
    "errors"
)

// Sign signs a message with a private key.
func Sign(m []byte, sk *ecdsa.PrivateKey) ([]byte, error) {
    if sk == nil {
        return nil, errors.New("private key is nil")
    }

    r, s, err := ecdsa.SignASN1(rand.Reader, sk, m)
    if err != nil {
        return nil, err
    }

    // DER encode the signature
    return x509.MarshalPKCS1v15Signature(r, s)
}

func main() {
    // Example usage:
    // Generate a new ECDSA private key
    sk, err := ecdsa.GenerateKey(ecdsa.P256(), rand.Reader)
    if err != nil {
        panic(err)
    }

    message := []byte("Hello, World!")
    signature, err := Sign(message, sk)
    if err != nil {
        panic(err)
    }

    // Use the signature as needed
    println(string(signature))
}