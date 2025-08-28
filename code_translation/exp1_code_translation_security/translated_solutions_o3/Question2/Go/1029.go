package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha512"
	"encoding/hex"
	"fmt"
	"log"
	"math/big"
)

/* ---------- internal helpers ---------- */

// fixed length left-padded byte slice (“to_string()” in the Python code)
func padBytes(b []byte, size int) []byte {
	if len(b) >= size {
		return b
	}
	out := make([]byte, size)
	copy(out[size-len(b):], b)
	return out
}

// encode r|s as raw concatenation (the default of python-ecdsa)
func encodeSignature(r, s *big.Int, c elliptic.Curve) []byte {
	size := (c.Params().BitSize + 7) / 8 // 48 bytes for P-384
	sig := make([]byte, 2*size)

	rb := r.Bytes()
	sb := s.Bytes()

	copy(sig[size-len(rb):size], rb)
	copy(sig[2*size-len(sb):], sb)

	return sig
}

// reverse operation of encodeSignature
func decodeSignature(sig []byte, c elliptic.Curve) (r, s *big.Int, err error) {
	size := (c.Params().BitSize + 7) / 8
	if len(sig) != 2*size {
		return nil, nil, fmt.Errorf("invalid raw signature length")
	}
	r = new(big.Int).SetBytes(sig[:size])
	s = new(big.Int).SetBytes(sig[size:])
	return
}

/* ---------- API functions (names match the python file) ---------- */

// ecdsa_genkey()
func ecdsaGenKey() (*ecdsa.PrivateKey, *ecdsa.PublicKey, error) {
	sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		return nil, nil, err
	}
	return sk, &sk.PublicKey, nil
}

// ecdsa_sign(sk, message)
func ecdsaSign(sk *ecdsa.PrivateKey, msg []byte) ([]byte, error) {
	hash := sha512.Sum384(msg)

	r, s, err := ecdsa.Sign(rand.Reader, sk, hash[:])
	if err != nil {
		return nil, err
	}
	return encodeSignature(r, s, sk.Curve), nil
}

// ecdsa_verify_key(vk, signature, message)
func ecdsaVerifyKey(vk *ecdsa.PublicKey, sig, msg []byte) (bool, error) {
	r, s, err := decodeSignature(sig, vk.Curve)
	if err != nil {
		return false, err
	}
	hash := sha512.Sum384(msg)
	return ecdsa.Verify(vk, hash[:], r, s), nil
}

/* ---------- optional helper mirroring python’s ecdsa_key() ---------- */

func ecdsaKey(msg []byte) ([]byte, error) {
	sk, vk, err := ecdsaGenKey()
	if err != nil {
		return nil, err
	}
	signature, err := ecdsaSign(sk, msg)
	if err != nil {
		return nil, err
	}
	ok, err := ecdsaVerifyKey(vk, signature, msg)
	if err != nil {
		return nil, err
	}
	fmt.Println(ok) // will print “true”
	return signature, nil
}

/* -------------------------------- main -------------------------------- */

func main() {
	message := []byte("This is a test message")

	// Equivalent to python’s “sk, vk = ecdsa_genkey()”
	sk, vk, err := ecdsaGenKey()
	if err != nil {
		log.Fatal(err)
	}

	// Print private key (D) and public key (X||Y) in hex
	// Python’s to_string() always gives 48-byte slices on P-384
	keySize := (sk.Curve.Params().BitSize + 7) / 8

	fmt.Println(hex.EncodeToString(padBytes(sk.D.Bytes(), keySize)))
	fmt.Println(
		hex.EncodeToString(padBytes(vk.X.Bytes(), keySize)) +
			hex.EncodeToString(padBytes(vk.Y.Bytes(), keySize)),
	)

	// Sign and print the raw signature
	signature, err := ecdsaSign(sk, message)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(hex.EncodeToString(signature))

	// Verify and print the boolean result
	ok, err := ecdsaVerifyKey(vk, signature, message)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(ok)
}