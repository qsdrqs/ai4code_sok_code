package main

import (
    "crypto/ecdsa"
    "crypto/sha256"
    "encoding/asn1"
    "errors"
)

// SignMessage signs a given message using a given ECDSA signing key
func SignMessage(message []byte, signingKey *ecdsa.PrivateKey) ([]byte, error) {
    // Check if signing key is nil
    if signingKey == nil {
        return nil, errors.New("signing key cannot be nil")
    }

    // Hash the message
    messageHash := sha256.Sum256(message)

    // Sign the message hash
    r, s, err := ecdsa.SignASN1(signingKey, messageHash[:], nil)
    if err != nil {
        return nil, err
    }

    // DER encode the signature
    signature, err := asn1.Marshal(struct {
        R, S *big.Int
    }{
        R: r,
        S: s,
    })
    if err != nil {
        return nil, err
    }

    return signature, nil
}

func main() {
    // Example usage
    // Note: You need to generate or provide your ECDSA private key
    // For demonstration purposes, assume we have a private key
    // privateKey, err := ecdsa.GenerateKey(ecdsa.P256(), rand.Reader)
    // if err != nil {
    //     log.Fatal(err)
    // }

    // message := []byte("Hello, World!")
    // signature, err := SignMessage(message, privateKey)
    // if err != nil {
    //     log.Fatal(err)
    // }
    // log.Println(signature)
}