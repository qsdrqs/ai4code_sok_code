package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/asn1"
    "fmt"
    "log"
)

type ECDSASignature struct {
    R *big.Int
    S *big.Int
}

// sign signs a given message using a given ECDSA signing key
func sign(message []byte, key *ecdsa.PrivateKey) ([]byte, error) {
    r, s, err := ecdsa.Sign(rand.Reader, key, message)
    if err != nil {
        return nil, err
    }
    // Convert r and s to DER format
    return asn1.Marshal(ECDSASignature{R: r, S: s})
}

func main() {
    // Generate a new ECDSA key pair
    sk, err := ecdsa.GenerateKey(elliptic.SECP256k1(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }
    vk := sk.Public()

    // Sign a message
    message := []byte("helloworld")
    signedMessage, err := sign(message, sk)
    if err != nil {
        log.Fatal(err)
    }

    // Verify the signature
    var sig ECDSASignature
    _, err = asn1.Unmarshal(signedMessage, &sig)
    if err != nil {
        log.Fatal(err)
    }
    valid := ecdsa.Verify(vk, message, sig.R, sig.S)
    fmt.Println(valid) // Should print: true
}