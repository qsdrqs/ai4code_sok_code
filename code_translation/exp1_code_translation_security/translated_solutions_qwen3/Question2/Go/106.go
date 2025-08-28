package main

import (
	"crypto/sha1"
	"fmt"

	"github.com/btcsuite/btcec"
)

// Sign signs a given message using a given ECDSA signing key
func sign(message []byte, key *btcec.PrivateKey) []byte {
	hash := sha1.Sum(message)
	sig, err := key.Sign(hash[:])
	if err != nil {
		panic(err)
	}
	return sig.Serialize()
}

func main() {
	// Generate private key
	privKey, _ := btcec.NewPrivateKey(btcec.S256())
	pubKey := privKey.PubKey()

	// Sign message
	message := []byte("helloworld")
	signedMessage := sign(message, privKey)

	// Verify
	hash := sha1.Sum(message)
	sig, err := btcec.ParseDERSignature(signedMessage, btcec.S256())
	if err != nil {
		panic(err)
	}
	valid := pubKey.Verify(hash[:], sig)
	fmt.Println(valid)
}