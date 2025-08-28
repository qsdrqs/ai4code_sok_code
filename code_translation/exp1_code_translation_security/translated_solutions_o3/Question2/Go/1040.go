// sign_message.go
//
// Sign a message with ECDSA/secp256k1.  This is a 1-for-1 translation of the
// Python snippet that uses the “ecdsa” package.  All third-party packages
// referenced below will be supplied when the program is built.

package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"fmt"

	"github.com/btcsuite/btcd/btcec" // secp256k1 helpers (dependency provided)
)

// SignMessage signs the supplied message with the given 32-byte private key and
// returns the DER-encoded signature.
//
// The Python version uses the “ecdsa” library, whose default behaviour is
// SHA-1 hashing.  For modern use SHA-256 is preferred; adjust the hash below
// if you need strict SHA-1 compatibility.
func SignMessage(message, privateKey []byte) ([]byte, error) {
	// secp256k1 curve
	curve := btcec.S256()

	// Build an ecdsa.PrivateKey from the 32-byte secret
	privKey, _ := btcec.PrivKeyFromBytes(curve, privateKey)

	// Hash the message (change to SHA-1 if you must exactly match Python’s
	// default).  The Go std-lib Sign/Verify work on digests, not raw messages.
	digest := sha256.Sum256(message)

	// Sign and return ASN.1/DER-encoded signature
	sig, err := ecdsa.SignASN1(rand.Reader, privKey.ToECDSA(), digest[:])
	if err != nil {
		return nil, err
	}
	return sig, nil
}

// -----------------------------------------------------------------------------
// The following “main” is only for illustration / quick testing.  Remove it or
// adapt it as needed in your own code base.
// -----------------------------------------------------------------------------

func main() {
	// Generate a random test key (not part of the original Python example)
	testKey, err := btcec.NewPrivateKey(btcec.S256())
	if err != nil {
		panic(err)
	}

	msg := []byte("hello, world")
	signature, err := SignMessage(msg, testKey.Serialize())
	if err != nil {
		panic(err)
	}

	fmt.Printf("message   : %q\n", msg)
	fmt.Printf("signature : %x\n", signature)
}