package main

import (
	"fmt"

	"github.com/starkbank/ecdsa-go/v2/ellipticcurve/ecdsa"
	"github.com/starkbank/ecdsa-go/v2/ellipticcurve/privatekey"
)

// Library used https://github.com/starkbank/ecdsa-go
func main() {
	// Generate Keys
	privateKey := privatekey.New()
	publicKey := privateKey.PublicKey()

	message := "My test message"

	// Generate Signature
	signature := ecdsa.Sign(message, privateKey)

	// Verify if signature is valid
	fmt.Println(ecdsa.Verify(message, signature, publicKey))
}