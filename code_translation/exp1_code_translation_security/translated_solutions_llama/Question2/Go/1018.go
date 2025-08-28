package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/hex"
    "errors"
    "log"
)

// Sign signs a message with the given private key.
func Sign(msg []byte, key *ecdsa.PrivateKey) ([]byte, error) {
    if key == nil {
        return nil, errors.New("private key is nil")
    }

    r, s, err := ecdsa.Sign(rand.Reader, key, msg)
    if err != nil {
        return nil, err
    }

    // DER encoding of signature
    sig := append(r.Bytes(), s.Bytes()...)
    return sig, nil
}

func main() {
    // Generate a new private key for demonstration purposes
    key, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }

    msg := []byte("message")
    sig, err := Sign(msg, key)
    if err != nil {
        log.Fatal(err)
    }

    log.Printf("Signature: %x\n", sig)
}