package main

import (
	"fmt"

	"github.com/starkbank/ecdsa-go/v2/ellipticcurve/ecdsa"
	"github.com/starkbank/ecdsa-go/v2/ellipticcurve/privateKey"
)

func main() {
	privKey := privateKey.New()
	pubKey := privKey.PublicKey()

	message := "Test message"

	signature := ecdsa.Sign(message, privKey)

	fmt.Println(ecdsa.Verify(message, signature, pubKey))
}