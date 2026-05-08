package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
)

// https://pkg.go.dev/crypto/ecdsa#SignASN1
func signString(privateKey *ecdsa.PrivateKey, data []byte) ([]byte, error) {
	// privateKey, _ := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	// data := []byte("this is some data I'd like to sign")
	hash := sha256.Sum256(data)
	signature, err := ecdsa.SignASN1(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}
	return signature, nil
}

/*
import (
	"crypto/elliptic"
	"encoding/asn1"
	"fmt"
	"math/big"
)

func main() {
	privateKey, _ := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	signature, _ := signString(privateKey, []byte("hello world"))

	var sig struct{ R, S *big.Int }
	asn1.Unmarshal(signature, &sig)
	fmt.Printf("(%v, %v)\n", sig.R, sig.S)
}
*/