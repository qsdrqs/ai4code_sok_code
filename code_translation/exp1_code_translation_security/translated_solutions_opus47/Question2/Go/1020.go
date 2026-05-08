package main

import (
	"crypto/sha256"
	"fmt"

	"github.com/btcsuite/btcd/btcec/v2"
	"github.com/btcsuite/btcd/btcec/v2/ecdsa"
)

var (
	sk *btcec.PrivateKey
	vk *btcec.PublicKey
)

func signMessage(sk *btcec.PrivateKey, message []byte) bool {
	hash := sha256.Sum256(message)
	sig := ecdsa.Sign(sk, hash[:])
	return sig.Verify(hash[:], vk)
}

func main() {
	var err error
	sk, err = btcec.NewPrivateKey()
	if err != nil {
		panic(err)
	}
	vk = sk.PubKey()

	fmt.Println(signMessage(sk, []byte("..")))
}