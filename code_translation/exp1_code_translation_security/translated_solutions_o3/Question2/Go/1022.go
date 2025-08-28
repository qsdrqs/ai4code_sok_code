package main

import (
	"fmt"

	"github.com/starkbank/ecdsa-go/ecdsa"
	"github.com/starkbank/ecdsa-go/ellipticcurve"
)

func main() {
	// Generate keys
	privateKey := ellipticcurve.GeneratePrivateKey()
	publicKey  := privateKey.PublicKey()

	message := "My test message"

	// Sign the message
	signature := ecdsa.Sign(message, privateKey)

	// Verify the signature
	fmt.Println(ecdsa.Verify(message, signature, publicKey))
}