package signer

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha1"
	"encoding/asn1"
	"fmt"

	"github.com/btcsuite/btcd/btcec/v2" // secp256k1 implementation
)

// SignMessage signs message with a secp256k1 private key.
//
// It reproduces the behaviour of:
//
//     signer = ecdsa.SigningKey.from_string(privKey, curve=ecdsa.SECP256k1)
//     sig    = signer.sign(message)
//
// in the Python-ecdsa library, i.e. SHA-1 hashing + DER encoding.
func SignMessage(message, privKeyBytes []byte) ([]byte, error) {
	// 1. Parse the raw 32-byte scalar into a usable *ecdsa.PrivateKey.
	if len(privKeyBytes) != btcec.PrivKeyBytesLen {
		return nil, fmt.Errorf("invalid key length: got %d, want %d",
			len(privKeyBytes), btcec.PrivKeyBytesLen)
	}
	curve := btcec.S256()
	priv, _ := btcec.PrivKeyFromBytes(curve, privKeyBytes) // never fails

	// 2. Hash the message with SHA-1 (the python-ecdsa default).
	digest := sha1.Sum(message)

	// 3. Generate the (r,s) ECDSA signature.
	r, s, err := ecdsa.Sign(rand.Reader, priv.ToECDSA(), digest[:])
	if err != nil {
		return nil, fmt.Errorf("signing failed: %w", err)
	}

	// 4. Encode as ASN.1 DER – same format returned by python-ecdsa.
	type ecdsaSignature struct {
		R, S *big.Int
	}
	return asn1.Marshal(ecdsaSignature{r, s})
}