package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "crypto/sha256"
    "encoding/hex"
    "fmt"
    "log"
)

// sign signs the given data with the provided private key using ECDSA and SHA256.
func sign(key *ecdsa.PrivateKey, data []byte) ([]byte, error) {
    hash := sha256.Sum256(data)
    r, s, err := ecdsa.SignASN1(rand.Reader, &key.PublicKey, hash[:])
    if err != nil {
        return nil, err
    }
    // Convert r and s to DER format
    derBytes := append([]byte{0x30}, 0) // 0x30 is the tag for SEQUENCE
    rBytes := elliptic.MarshalASN1(r)
    sBytes := elliptic.MarshalASN1(s)
    derBytes = append(derBytes, append(append([]byte{0x02, byte(len(rBytes))}, rBytes...), append([]byte{0x02, byte(len(sBytes))}, sBytes...)...)...)
    derBytes[1] = byte(len(derBytes) - 2) // Update length
    return derBytes, nil
}

func main() {
    // Generate a new private key
    privateKey, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }

    // Data to be signed
    data := []byte("this is some data I'd like to sign")

    // Sign the data
    signature, err := sign(privateKey, data)
    if err != nil {
        log.Fatal(err)
    }

    // Print the signature in hexadecimal
    fmt.Println(hex.EncodeToString(signature))
}