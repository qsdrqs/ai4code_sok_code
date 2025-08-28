package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/pem"
    "errors"
    "fmt"
    "log"

    "golang.org/x/crypto/pkix"
)

// SignMessage signs a given message using a given ECDSA signing key.
func SignMessage(message []byte, curveName string) ([]byte, error) {
    // Define supported curves
    curves := map[string]elliptic.Curve{
        "P256": elliptic.P256(),
        "P384": elliptic.P384(),
        "P521": elliptic.P521(),
    }

    // Check if the curve is supported
    curve, ok := curves[curveName]
    if !ok {
        return nil, errors.New("unsupported curve")
    }

    // Generate a new ECDSA private key
    privateKey, err := ecdsa.GenerateKey(curve, rand.Reader)
    if err != nil {
        return nil, err
    }

    // Sign the message
    r, s, err := ecdsa.SignASN1(rand.Reader, privateKey, message)
    if err != nil {
        return nil, err
    }

    // Marshal the signature
    signature := append(r.Bytes(), s.Bytes()...)
    return signature, nil
}

func main() {
    message := []byte("Hello, World!")
    curveName := "P256"
    signature, err := SignMessage(message, curveName)
    if err != nil {
        log.Fatal(err)
    }
    fmt.Printf("Signature: %x\n", signature)
}