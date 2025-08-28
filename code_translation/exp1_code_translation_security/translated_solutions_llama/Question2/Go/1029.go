package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/hex"
    "fmt"
    "log"

    "github.com/btcsuite/btcutil/ecdsa"
)

// ecdsaKey generates a new ECDSA key pair, signs a message, and verifies the signature.
func ecdsaKey(message []byte) ([]byte, *ecdsa.PublicKey, error) {
    // Generate a new ECDSA private key
    sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
    if err != nil {
        return nil, nil, err
    }

    // Get the verifying key (public key)
    vk := sk.Public()

    // Sign the message
    r, s, err := ecdsa.Sign(rand.Reader, sk, message)
    if err != nil {
        return nil, nil, err
    }

    // Convert the signature to DER format
    signature := ecdsa.MarshalPKCS1v15(vk, r, s)

    // Verify the signature
    valid := ecdsa.Verify(vk, message, r, s)
    fmt.Println(valid)

    return signature, vk, nil
}

// ecdsaVerify verifies a signature given a message, signature, and verifying key.
func ecdsaVerify(signature []byte, message []byte, vk *ecdsa.PublicKey) bool {
    // Parse the DER-encoded signature
    r, s, err := ecdsa.ParsePKCS1v15Signature(signature)
    if err != nil {
        return false
    }

    // Verify the signature
    return ecdsa.Verify(vk, message, r, s)
}

// ecdsaGenKey generates a new ECDSA key pair.
func ecdsaGenKey() (*ecdsa.PrivateKey, *ecdsa.PublicKey) {
    // Generate a new ECDSA private key
    sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }

    // Get the verifying key (public key)
    vk := sk.Public()

    return sk, vk
}

// ecdsaSign signs a message given a private key.
func ecdsaSign(sk *ecdsa.PrivateKey, message []byte) ([]byte, error) {
    // Sign the message
    r, s, err := ecdsa.Sign(rand.Reader, sk, message)
    if err != nil {
        return nil, err
    }

    // Convert the signature to DER format
    return ecdsa.MarshalPKCS1v15(sk.Public(), r, s), nil
}

// ecdsaVerifyKey verifies a signature given a verifying key, signature, and message.
func ecdsaVerifyKey(vk *ecdsa.PublicKey, signature []byte, message []byte) bool {
    // Parse the DER-encoded signature
    r, s, err := ecdsa.ParsePKCS1v15Signature(signature)
    if err != nil {
        return false
    }

    // Verify the signature
    return ecdsa.Verify(vk, message, r, s)
}

func main() {
    message := []byte("This is a test message")
    sk, vk := ecdsaGenKey()

    // Print the private key
    skBytes := x509.MarshalPKCS1PrivateKey(sk)
    fmt.Println(hex.EncodeToString(skBytes))

    // Print the public key
    vkBytes := x509.MarshalPKCS1PublicKey(vk)
    fmt.Println(hex.EncodeToString(vkBytes))

    // Sign the message
    signature, err := ecdsaSign(sk, message)
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println(hex.EncodeToString(signature))

    // Verify the signature
    valid := ecdsaVerifyKey(vk, signature, message)
    fmt.Println(valid)
}