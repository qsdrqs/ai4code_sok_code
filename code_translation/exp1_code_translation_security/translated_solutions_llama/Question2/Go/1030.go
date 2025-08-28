package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "crypto/sha3"
    "encoding/hex"
    "fmt"
    "math/big"
)

// Function to generate SHA3-256 hash of a message
func sha3_256Hash(msg string) *big.Int {
    hash := sha3.Sum256([]byte(msg))
    return new(big.Int).SetBytes(hash[:])
}

// Function to sign a message using ECDSA with secp256k1 curve and SHA3-256 hash
func signECDSAsecp256k1(msg string, privKey *ecdsa.PrivateKey) (*ecdsa.Signature, error) {
    msgHash := sha3_256Hash(msg)
    signature, err := ecdsa.SignASN1(rand.Reader, privKey, msgHash.Bytes())
    if err != nil {
        return nil, err
    }
    r := new(big.Int).SetBytes(signature[:32])
    s := new(big.Int).SetBytes(signature[32:64])
    return &ecdsa.Signature{R: r, S: s}, nil
}

// Function to verify a signature using ECDSA with secp256k1 curve and SHA3-256 hash
func verifyECDSAsecp256k1(msg string, signature *ecdsa.Signature, pubKey *ecdsa.PublicKey) bool {
    msgHash := sha3_256Hash(msg)
    return ecdsa.Verify(pubKey, msgHash.Bytes(), signature.R, signature.S)
}

func main() {
    // ECDSA sign message (using the curve secp256k1 + SHA3-256)
    msg := "Message for ECDSA signing"

    // Generate a new private key
    privKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
    if err != nil {
        fmt.Println(err)
        return
    }

    // Sign the message
    signature, err := signECDSAsecp256k1(msg, privKey)
    if err != nil {
        fmt.Println(err)
        return
    }

    // Print the results
    fmt.Println("Message:", msg)
    fmt.Println("Private key:", hex.EncodeToString(privKey.D.Bytes()))
    fmt.Println("Public key:", hex.EncodeToString(xToBytes(privKey.PublicKey.X)), hex.EncodeToString(yToBytes(privKey.PublicKey.Y)))
    fmt.Println("Signature: r=", hex.EncodeToString(signature.R.Bytes()), ", s=", hex.EncodeToString(signature.S.Bytes()))

    // Verify the signature
    valid := verifyECDSAsecp256k1(msg, signature, &privKey.PublicKey)
    fmt.Println("Is signature valid?", valid)
}

// Helper functions to convert big.Int to byte slices
func xToBytes(x *big.Int) []byte {
    b := make([]byte, 32)
    if x.Sign() < 0 {
        x.Add(x, elliptic.P256().Params().N)
    }
    x.FillBytes(b)
    return b
}

func yToBytes(y *big.Int) []byte {
    b := make([]byte, 32)
    if y.Sign() < 0 {
        y.Add(y, elliptic.P256().Params().N)
    }
    y.FillBytes(b)
    return b
}