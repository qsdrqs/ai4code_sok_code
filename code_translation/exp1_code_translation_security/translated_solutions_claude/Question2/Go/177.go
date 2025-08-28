package main

import (
    "crypto"
    "crypto/rand"
    "crypto/rsa"
    "crypto/sha256"
)

func sign(m []byte, sk *rsa.PrivateKey) ([]byte, error) {
    hashed := sha256.Sum256(m)
    signature, err := rsa.SignPKCS1v15(rand.Reader, sk, crypto.SHA256, hashed[:])
    if err != nil {
        return nil, err
    }
    return signature, nil
}