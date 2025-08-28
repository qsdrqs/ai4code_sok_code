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

// GenerateECDSAKey generates a new ECDSA key pair.
func GenerateECDSAKey(curve elliptic.Curve) (*ecdsa.PrivateKey, error) {
    return ecdsa.GenerateKey(curve, rand.Reader)
}

// Sign signs a given message using an ECDSA private key.
func Sign(privateKey *ecdsa.PrivateKey, message []byte) ([]byte, error) {
    r, s, err := ecdsa.SignASN1(rand.Reader, privateKey, message)
    if err != nil {
        return nil, err
    }
    // DER encode the signature
    return encodeDERSignature(r, s)
}

// Verify verifies a given signature using an ECDSA public key.
func Verify(publicKey *ecdsa.PublicKey, message, signature []byte) (bool, error) {
    r, s, err := decodeDERSignature(signature)
    if err != nil {
        return false, err
    }
    return ecdsa.VerifyASN1(publicKey, message, append(r.Bytes(), s.Bytes()...)), nil
}

// Helper function to encode DER signature
func encodeDERSignature(r, s *big.Int) ([]byte, error) {
    rBytes := r.Bytes()
    sBytes := s.Bytes()
    // Ensure both r and s are the same length
    maxLen := max(len(rBytes), len(sBytes))
    paddedR := padToLength(rBytes, maxLen)
    paddedS := padToLength(sBytes, maxLen)

    // DER encode
    der := []byte{0x30}
    der = append(der, byte(2+len(paddedR)+len(paddedS)))
    der = append(der, 0x02)
    der = append(der, byte(len(paddedR)))
    der = append(der, paddedR...)
    der = append(der, 0x02)
    der = append(der, byte(len(paddedS)))
    der = append(der, paddedS...)
    return der, nil
}

// Helper function to decode DER signature
func decodeDERSignature(der []byte) (*big.Int, *big.Int, error) {
    if len(der) < 4 || der[0] != 0x30 {
        return nil, nil, errors.New("invalid DER signature")
    }
    l := 2
    for i := 1; i < len(der); i++ {
        if der[i] == 0x02 {
            l += 2 + int(der[i+1])
            if l >= len(der) {
                return nil, nil, errors.New("invalid DER signature")
            }
        }
    }
    offset := 2
    rLen := int(der[offset])
    offset += 1 + rLen
    sLen := int(der[offset])
    r := new(big.Int).SetBytes(der[2 : 2+rLen])
    s := new(big.Int).SetBytes(der[2+rLen : 2+rLen+sLen])
    return r, s, nil
}

// Helper function to pad to a certain length
func padToLength(b []byte, length int) []byte {
    if len(b) == length {
        return b
    }
    padded := make([]byte, length)
    copy(padded[len(padded)-len(b):], b)
    return padded
}

// Helper function to get max of two numbers
func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}

func main() {
    // Generate a new ECDSA key pair
    privateKey, err := GenerateECDSAKey(elliptic.P256())
    if err != nil {
        log.Fatal(err)
    }

    // Sign a message
    message := []byte("Hello, World!")
    signature, err := Sign(privateKey, message)
    if err != nil {
        log.Fatal(err)
    }

    // Verify the signature
    valid, err := Verify(&privateKey.PublicKey, message, signature)
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println(valid) // Should print: true
}