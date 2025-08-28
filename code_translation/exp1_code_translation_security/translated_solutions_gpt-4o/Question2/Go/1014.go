package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

// signMessage signs a given message using a given ECDSA signing key
func signMessage(message []byte, privateKey *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message
	messageHash := sha256.Sum256(message)

	// Sign the message hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, messageHash[:])
	if err != nil {
		return nil, err
	}

	// Encode the signature in DER format
	signature, err := encodeDER(r, s)
	if err != nil {
		return nil, err
	}

	return signature, nil
}

// encodeDER encodes the signature (r, s) in DER format
func encodeDER(r, s *big.Int) ([]byte, error) {
	// DER encoding format: https://tools.ietf.org/html/rfc5480#section-2.2
	rBytes := r.Bytes()
	sBytes := s.Bytes()

	// Ensure the integers are properly padded
	if len(rBytes) > 0 && rBytes[0]&0x80 != 0 {
		rBytes = append([]byte{0x00}, rBytes...)
	}
	if len(sBytes) > 0 && sBytes[0]&0x80 != 0 {
		sBytes = append([]byte{0x00}, sBytes...)
	}

	// Construct the DER-encoded signature
	der := []byte{0x30, 0x00} // SEQUENCE tag and placeholder for length
	der = append(der, 0x02, byte(len(rBytes))) // INTEGER tag and length for r
	der = append(der, rBytes...)
	der = append(der, 0x02, byte(len(sBytes))) // INTEGER tag and length for s
	der = append(der, sBytes...)

	// Update the length of the SEQUENCE
	der[1] = byte(len(der) - 2)

	return der, nil
}

func main() {
	// Example usage
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		fmt.Println("Error generating key:", err)
		return
	}

	message := []byte("Hello, ECDSA!")
	signature, err := signMessage(message, privateKey)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	fmt.Printf("Signature: %x\n", signature)
}