package main

import (
	"github.com/btcsuite/btcd/btcec/v2"
	"github.com/btcsuite/btcd/btcec/v2/ecdsa"
)

func myfunction(message []byte) {
	sk, _ := btcec.NewPrivateKey()
	sig := ecdsa.Sign(sk, message)
	_ = sig
}