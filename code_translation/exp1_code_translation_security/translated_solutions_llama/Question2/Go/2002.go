package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/hex"
    "fmt"
    "log"
)

// sign generates a signature for a given message using the provided private key.
func sign(sk *ecdsa.PrivateKey, m string) ([]byte, error) {
    // Hash the message
    hash := elliptic.P256().New()
    _, err := hash.Write([]byte(m))
    if err != nil {
        return nil, err
    }
    r, s, err := ecdsa.Sign(rand.Reader, sk, hash.Sum(nil))
    if err != nil {
        return nil, err
    }
    // Marshal the signature
    return encodeSignature(r, s)
}

// encodeSignature encodes the ECDSA signature into a byte slice.
func encodeSignature(r, s *big.Int) ([]byte, error) {
    rBytes := r.Bytes()
    sBytes := s.Bytes()
    // Ensure both r and s are 32 bytes long (256 bits)
    rBytes = padBytes(rBytes, 32)
    sBytes = padBytes(sBytes, 32)
    // Concatenate r and s
    signature := make([]byte, 64)
    copy(signature, rBytes)
    copy(signature[32:], sBytes)
    return signature, nil
}

// padBytes pads a byte slice with leading zeros to a specified length.
func padBytes(b []byte, length int) []byte {
    padded := make([]byte, length)
    copy(padded[length-len(b):], b)
    return padded
}

// verify checks if a signature is valid for a given message and public key.
func verify(vk *ecdsa.PublicKey, signature []byte, m string) bool {
    // Hash the message
    hash := elliptic.P256().New()
    _, err := hash.Write([]byte(m))
    if err != nil {
        return false
    }
    r := new(big.Int).SetBytes(signature[:32])
    s := new(big.Int).SetBytes(signature[32:])
    return ecdsa.Verify(vk, hash.Sum(nil), r, s)
}

func main() {
    // Generate a new ECDSA key pair
    sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }
    vk := sk.Public()

    // Sign a message
    message := "hello world"
    signature, err := sign(sk, message)
    if err != nil {
        log.Fatal(err)
    }

    // Verify the signature with a wrong message
    wrongMessage := "hello worfld"
    valid := verify(vk, signature, wrongMessage)
    fmt.Printf("Is signature valid for '%s'? %t\n", wrongMessage, valid)

    // Verify the signature with the correct message
    valid = verify(vk, signature, message)
    fmt.Printf("Is signature valid for '%s'? %t\n", message, valid)
}