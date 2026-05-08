package main

import (
	"fmt"

	"github.com/starkbank/ecdsa-go/v2/ellipticcurve/ecdsa"
	"github.com/starkbank/ecdsa-go/v2/ellipticcurve/privatekey"
	"github.com/starkbank/ecdsa-go/v2/ellipticcurve/signature"
)

func generateEcdsaSignedMsg(message string, privateKey *privatekey.PrivateKey) *signature.Signature {
	return ecdsa.Sign(message, privateKey)
}

func main() {
	// Generate Keys
	privateKey := privatekey.New()
	publicKey := privateKey.PublicKey()
	_ = publicKey // generated but unused, mirroring the Python code

	message := "My test message"
	sig := generateEcdsaSignedMsg(message, privateKey)
	fmt.Println(sig.ToBase64())
}