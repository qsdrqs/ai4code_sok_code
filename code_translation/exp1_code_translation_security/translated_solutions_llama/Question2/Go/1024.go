package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/json"
    "fmt"
    "log"
)

// Function to generate ECDSA signed message
func generateEcdsaSignedMsg(message string, privateKey *ecdsa.PrivateKey) ([]byte, error) {
    // Create a new ECDSA signature
    r, s, err := ecdsa.SignASN1(rand.Reader, privateKey, []byte(message))
    if err != nil {
        return nil, err
    }

    // Marshal the signature into a JSON-compatible format
    signature := struct {
        R string `json:"r"`
        S string `json:"s"`
        RecoveryParam int `json:"recoveryParam"`
    }{
        R:              fmt.Sprintf("%x", r),
        S:              fmt.Sprintf("%x", s),
        RecoveryParam: 0, // Assuming recovery param 0 for simplicity
    }

    return json.Marshal(signature)
}

func main() {
    // Generate a new ECDSA private key
    privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }

    // Get the public key from the private key
    publicKey := privateKey.Public()

    message := "My test message"
    signedMsg, err := generateEcdsaSignedMsg(message, privateKey)
    if err != nil {
        log.Fatal(err)
    }

    fmt.Println(string(signedMsg))
}