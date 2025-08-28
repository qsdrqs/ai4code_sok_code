package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/sha256"
    "encoding/hex"
    "fmt"
    "math/big"
)

// signMessage signs a message using the provided private key.
func signMessage(message string, privateKeyBytes []byte) (string, error) {
    // Parse the private key
    x, y, d := new(big.Int).SetBytes(privateKeyBytes[:32]), new(big.Int).SetBytes(privateKeyBytes[32:64]), new(big.Int).SetBytes(privateKeyBytes[64:])
    privateKey := ecdsa.PrivateKey{
        PublicKey: ecdsa.PublicKey{
            Curve: elliptic.SECP256k1,
            X:     x,
            Y:     y,
        },
        D: d,
    }

    // Hash the message
    messageHash := sha256.Sum256([]byte(message))

    // Sign the message
    r, s, err := ecdsa.Sign(&privateKey, messageHash[:], nil)
    if err != nil {
        return "", err
    }

    // DER encode the signature
    derEncoded := elliptic.MarshalPKCS1(elliptic.SECP256k1, r, s)

    // Return the DER encoded signature as a hexadecimal string
    return hex.EncodeToString(derEncoded), nil
}

func main() {
    // Test the function
    privateKeyHex := "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109"
    privateKeyBytes, err := hex.DecodeString(privateKeyHex)
    if err != nil {
        fmt.Println(err)
        return
    }

    message := "Hello"
    signature, err := signMessage(message, privateKeyBytes)
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Println(signature)
}