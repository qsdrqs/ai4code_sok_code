package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/pem"
    "errors"
    "log"

    "golang.org/x/crypto/pkix"
)

// SignMessage signs a given message using a provided private key.
func SignMessage(message []byte, signingKey *ecdsa.PrivateKey) ([]byte, error) {
    if signingKey == nil {
        return nil, errors.New("signing key cannot be nil")
    }

    r, s, err := ecdsa.SignASN1(rand.Reader, &signingKey.PublicKey, message)
    if err != nil {
        return nil, err
    }

    // Convert r and s to DER format
    derBytes := append([]byte{0x30}, variableLengthASN1(r, s)...)
    return derBytes, nil
}

// variableLengthASN1 encodes r and s into ASN.1 format.
func variableLengthASN1(r, s *big.Int) []byte {
    // Encode r
    rBytes := r.Bytes()
    rLen := len(rBytes)
    if rBytes[0] & 0x80 != 0 {
        rBytes = append([]byte{0x00}, rBytes...)
        rLen++
    }

    // Encode s
    sBytes := s.Bytes()
    sLen := len(sBytes)
    if sBytes[0] & 0x80 != 0 {
        sBytes = append([]byte{0x00}, sBytes...)
        sLen++
    }

    // Construct ASN.1 sequence
    asn1Len := 4 + rLen + sLen
    asn1 := make([]byte, asn1Len)

    asn1[0] = 0x30 // SEQUENCE
    asn1[1] = byte(asn1Len - 2) // Length
    asn1[2] = 0x02 // INTEGER
    asn1[3] = byte(rLen) // Length of r
    copy(asn1[4:4+rLen], rBytes)

    asn1[4+rLen] = 0x02 // INTEGER
    asn1[5+rLen] = byte(sLen) // Length of s
    copy(asn1[6+rLen:6+rLen+sLen], sBytes)

    return asn1
}

func main() {
    // Generate a new ECDSA private key
    privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }

    // Sign a message
    message := []byte("Hello, World!")
    signature, err := SignMessage(message, privateKey)
    if err != nil {
        log.Fatal(err)
    }

    log.Println("Signature:", signature)

    // PEM encode the private key for demonstration
    privateKeyPEM := pem.EncodeToMemory(
        &pem.Block{
            Type:  "EC PRIVATE KEY",
            Bytes: pem.EncodePKCS8PrivateKey(privateKey),
        },
    )
    log.Println("Private Key (PEM):", string(privateKeyPEM))
}