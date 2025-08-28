package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/hex"
    "errors"
    "log"

    "github.com/ethereum/go-ethereum/crypto"
)

// SignMessage signs a given message using an ECDSA private key.
func SignMessage(message []byte, privateKeyHex string) ([]byte, error) {
    // Convert private key from hex to ecdsa.PrivateKey
    privateKeyBytes, err := hex.DecodeString(privateKeyHex)
    if err != nil {
        return nil, err
    }

    privateKey, err := crypto.ToECDSA(privateKeyBytes)
    if err != nil {
        return nil, err
    }

    // Ensure the curve is SECP256k1
    if privateKey.Curve != elliptic.P256() {
        return nil, errors.New("only SECP256k1 curve is supported")
    }

    // Sign the message
    r, s, err := ecdsa.SignASN1(rand.Reader, privateKey, message)
    if err != nil {
        return nil, err
    }

    // Convert signature to DER format
    signature := append([]byte{0x30}, append(ecdsaSignatureToDER(r, s))...)

    return signature, nil
}

// ecdsaSignatureToDER converts ECDSA signature to DER format.
func ecdsaSignatureToDER(r, s *big.Int) []byte {
    rBytes := paddedBigInt(r, 32)
    sBytes := paddedBigInt(s, 32)

    return append(rBytes, sBytes...)
}

// paddedBigInt converts a big.Int to a 32-byte byte slice, padding with zeros if necessary.
func paddedBigInt(b *big.Int, length int) []byte {
    bytes := b.Bytes()
    padded := make([]byte, length)
    copy(padded[length-len(bytes):], bytes)
    return padded
}

import (
    "math/big"
)

func main() {
    // Example usage
    privateKeyHex := "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef"
    message := []byte("Hello, World!")

    signature, err := SignMessage(message, privateKeyHex)
    if err != nil {
        log.Fatal(err)
    }

    log.Printf("Signature: %x\n", signature)
}