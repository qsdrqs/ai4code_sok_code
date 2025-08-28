package yourpkg

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"math/big"
)

// signUsingECDSA replicates the behavior of the original Python helper.
// It signs the supplied message with the provided ECDSA private key and
// returns a DER-encoded (r,s) signature, the same format produced by
// cryptography-io’s key.signer(...).finalize().
func signUsingECDSA(key *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	// 1. Hash the message with SHA-256.
	digest := sha256.Sum256(message)

	// 2. Produce the ECDSA signature (r, s).
	r, s, err := ecdsa.Sign(rand.Reader, key, digest[:])
	if err != nil {
		return nil, err
	}

	// 3. Marshal (r, s) to DER so the output matches Python's finalize().
	type ecdsaSignature struct {
		R, S *big.Int
	}
	return asn1.Marshal(ecdsaSignature{R: r, S: s})
}