package ecdsautil

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"errors"
	"math/big"
)

/*
SignNIST256 replicates the behaviour of

    key = ecdsa.SigningKey.from_string(sk_raw, curve=ecdsa.NIST256p)
    key.sign(msg, hashfunc=hashlib.sha256)

in the original Python code.

signingKeyRaw must contain the 32-byte little-endian scalar “d”.
The returned byte-slice is an ASN.1/DER encoded (r,s) pair – the
default that the python-ecdsa package uses when no explicit sigencode
is given.
*/
func SignNIST256(message, signingKeyRaw []byte) ([]byte, error) {
	if len(signingKeyRaw) != 32 {
		return nil, errors.New("ecdsa: private key must be 32 bytes")
	}

	curve := elliptic.P256()

	d := new(big.Int).SetBytes(signingKeyRaw)
	if d.Sign() == 0 || d.Cmp(curve.Params().N) >= 0 {
		return nil, errors.New("ecdsa: invalid private scalar")
	}

	// Reconstruct the public part Q = d·G
	x, y := curve.ScalarBaseMult(signingKeyRaw)

	priv := &ecdsa.PrivateKey{
		PublicKey: ecdsa.PublicKey{
			Curve: curve,
			X:     x,
			Y:     y,
		},
		D: d,
	}

	hash := sha256.Sum256(message)

	r, s, err := ecdsa.Sign(rand.Reader, priv, hash[:])
	if err != nil {
		return nil, err
	}

	// Marshal (r,s) as ASN.1/DER just like python-ecdsa does by default.
	type ecdsaSignature struct {
		R, S *big.Int
	}
	return asn1.Marshal(ecdsaSignature{r, s})
}

/*
VerifyNIST256 replicates

    key = ecdsa.VerifyingKey.from_string(vk_raw, curve=ecdsa.NIST256p)
    key.verify(sig, msg, hashfunc=hashlib.sha256)

from the original Python.

verifyingKeyRaw must be 64 bytes: X‖Y, both big-endian without the
0x04 prefix.  The signature must be the ASN.1/DER blob produced by
SignNIST256 or by python-ecdsa’s default `sign()`.
*/
func VerifyNIST256(message, signature, verifyingKeyRaw []byte) bool {
	if len(verifyingKeyRaw) != 64 {
		return false
	}

	curve := elliptic.P256()
	x := new(big.Int).SetBytes(verifyingKeyRaw[:32])
	y := new(big.Int).SetBytes(verifyingKeyRaw[32:])

	if !curve.IsOnCurve(x, y) {
		return false
	}

	pub := &ecdsa.PublicKey{Curve: curve, X: x, Y: y}

	// Decode ASN.1/DER (r,s)
	type ecdsaSignature struct {
		R, S *big.Int
	}
	var sig ecdsaSignature
	if _, err := asn1.Unmarshal(signature, &sig); err != nil {
		return false
	}

	hash := sha256.Sum256(message)
	return ecdsa.Verify(pub, hash[:], sig.R, sig.S)
}