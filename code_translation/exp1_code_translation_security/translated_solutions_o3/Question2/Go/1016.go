package crypto

import (
	"crypto/sha1" // Python ecdsa.SigningKey.sign() defaults to SHA-1
	"errors"

	"github.com/btcsuite/btcd/btcec/v2" // secp256k1 implementation
)

// Sign signs the given message with the provided 32-byte secp256k1
// private key and returns the DER-encoded ECDSA signature.
//
// It mirrors the behaviour of the Python function:
//
//     signed_message = signature.sign(message)  # using hashlib.sha1
//
func Sign(message, privateKey []byte) ([]byte, error) {
	// sanity-check key length (secp256k1 private keys are 32 bytes)
	if len(privateKey) != 32 {
		return nil, errors.New("private key must be exactly 32 bytes")
	}

	// Construct a *btcec.PrivateKey from the raw 32-byte key.
	priv, _ := btcec.PrivKeyFromBytes(privateKey)

	// Hash the message using SHA-1 (to match Python’s default).
	digest := sha1.Sum(message)

	// Generate the ECDSA signature over the hash.
	sig, err := priv.Sign(digest[:])
	if err != nil {
		return nil, err
	}

	// Return the signature in DER format (same as Python ecdsa.sign()).
	return sig.SerializeDER(), nil
}