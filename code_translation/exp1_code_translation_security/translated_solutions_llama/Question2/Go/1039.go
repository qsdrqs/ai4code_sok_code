package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"encoding/asn1"
	"errors"
	"log"

	"golang.org/x/crypto/ecdsa"
)

// SignMessage signs a message with the provided ECDSA private key.
func SignMessage(message []byte, key *ecdsa.PrivateKey) ([]byte, error) {
	r, s, err := ecdsa.SignASN1(rand.Reader, key, message)
	if err != nil {
		return nil, err
	}

	// DER encode the signature
	signature := struct {
		R, S *big.Int
	}{
		R: r,
		S: s,
	}
	derBytes, err := asn1.Marshal(signature)
	if err != nil {
		return nil, err
	}

	return derBytes, nil
}

func main() {
	// Generate a new ECDSA private key
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatal(err)
	}

	// Sign a message
	message := []byte("message")
	signature, err := SignMessage(message, privateKey)
	if err != nil {
		log.Fatal(err)
	}

	log.Println("Signature:", signature)
}