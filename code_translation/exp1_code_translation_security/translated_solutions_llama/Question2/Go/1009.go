package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "fmt"
    "log"
)

func sign(sk *ecdsa.PrivateKey, message []byte) ([]byte, error) {
    r, s, err := ecdsa.Sign(rand.Reader, sk, message)
    if err != nil {
        return nil, err
    }

    // Convert signature to DER format
    rBytes := r.Bytes()
    sBytes := s.Bytes()
    sig := make([]byte, 0, 96)
    sig = append(sig, 0x30)
    sig = append(sig, 0x5a)
    sig = append(sig, 0x02)
    sig = append(sig, byte(len(rBytes)))
    sig = append(sig, rBytes...)
    sig = append(sig, 0x02)
    sig = append(sig, byte(len(sBytes)))
    sig = append(sig, sBytes...)
    return sig, nil
}

func verify(vk *ecdsa.PublicKey, sig []byte, message []byte) bool {
    r := new(big.Int)
    s := new(big.Int)
    index := 4
    r.SetBytes(sig[index : index+32])
    index += 32
    index += 2 // Skip 0x02 and length byte
    s.SetBytes(sig[index : index+32])

    return ecdsa.Verify(vk, message, r, s)
}

func main() {
    // Generate a new ECDSA private key
    sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
    if err != nil {
        log.Fatal(err)
    }

    // Define the message to be signed
    message := []byte("hehehe")

    // Sign the message
    sig, err := sign(sk, message)
    if err != nil {
        log.Fatal(err)
    }

    // Verify the signature
    vk := sk.Public()
    isValid := verify(vk, sig, message)
    fmt.Println(isValid)
}