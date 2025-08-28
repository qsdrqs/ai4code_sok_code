package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"fmt"
	"log"
)

// --- ECB Mode Implementation ---
// The Go standard library does not provide ECB mode due to its insecurity.
// We implement it here for a direct translation of the Python code.
// WARNING: ECB mode is not secure and should not be used in production systems.

type ecbEncrypter struct {
	b         cipher.Block
	blockSize int
}

func newECBEncrypter(b cipher.Block) cipher.BlockMode {
	return &ecbEncrypter{
		b:         b,
		blockSize: b.BlockSize(),
	}
}

func (x *ecbEncrypter) BlockSize() int { return x.blockSize }

func (x *ecbEncrypter) CryptBlocks(dst, src []byte) {
	if len(src)%x.blockSize != 0 {
		panic("crypto/cipher: input not full blocks")
	}
	if len(dst) < len(src) {
		panic("crypto/cipher: output smaller than input")
	}

	for len(src) > 0 {
		x.b.Encrypt(dst, src[:x.blockSize])
		src = src[x.blockSize:]
		dst = dst[x.blockSize:]
	}
}

type ecbDecrypter struct {
	b         cipher.Block
	blockSize int
}

func newECBDecrypter(b cipher.Block) cipher.BlockMode {
	return &ecbDecrypter{
		b:         b,
		blockSize: b.BlockSize(),
	}
}

func (x *ecbDecrypter) BlockSize() int { return x.blockSize }

func (x *ecbDecrypter) CryptBlocks(dst, src []byte) {
	if len(src)%x.blockSize != 0 {
		panic("crypto/cipher: input not full blocks")
	}
	if len(dst) < len(src) {
		panic("crypto/cipher: output smaller than input")
	}

	for len(src) > 0 {
		x.b.Decrypt(dst, src[:x.blockSize])
		src = src[x.blockSize:]
		dst = dst[x.blockSize:]
	}
}

// --- Translated Functions ---

// encode encodes a byte slice using an AES key in ECB mode.
// Note: The input length must be a multiple of the AES block size (16 bytes).
func encode(inp, key []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	if len(inp)%aes.BlockSize != 0 {
		return nil, fmt.Errorf("input is not a multiple of the block size")
	}

	encrypted := make([]byte, len(inp))
	encrypter := newECBEncrypter(block)
	encrypter.CryptBlocks(encrypted, inp)

	return encrypted, nil
}

// decode decodes a byte slice using an AES key in ECB mode.
func decode(inp, key []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	if len(inp)%aes.BlockSize != 0 {
		return nil, fmt.Errorf("input is not a multiple of the block size")
	}

	decrypted := make([]byte, len(inp))
	decrypter := newECBDecrypter(block)
	decrypter.CryptBlocks(decrypted, inp)



	return decrypted, nil
}

func main() {
	// In Python, b"..." creates a byte string. The Go equivalent is a byte slice []byte.
	inp := []byte("1111111111111111")
	key := []byte("1111111111111111")

	fmt.Printf("Original:  %s\n", inp)

	// Encode the input
	encoded, err := encode(inp, key)
	if err != nil {
		log.Fatalf("Encode failed: %v", err)
	}
	// Print encoded data as hex, which is standard for ciphertext
	fmt.Printf("Encoded:   %x\n", encoded)

	// Decode the result
	decoded, err := decode(encoded, key)
	if err != nil {
		log.Fatalf("Decode failed: %v", err)
	}
	fmt.Printf("Decoded:   %s\n", decoded)

	// The 'assert' statement from Python is translated to an if check.
	// We use bytes.Equal to compare the two byte slices.
	if !bytes.Equal(inp, decoded) {
		log.Fatal("Assertion failed: decoded text does not match original input")
	}

	fmt.Println("\nAssertion successful: The decoded text matches the original input.")
}