package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
)

// xorBytes XORs the corresponding elements of a and b into dst.
// All slices must have the same length.
func xorBytes(dst, a, b []byte) {
	for i := range dst {
		dst[i] = a[i] ^ b[i]
	}
}

// double multiplies b by 2 in GF(2^128) with irreducible polynomial 0x87,
// as required by OMAC.
func double(b []byte) []byte {
	result := make([]byte, len(b))
	msb := b[0] & 0x80
	for i := 0; i < len(b)-1; i++ {
		result[i] = (b[i] << 1) | (b[i+1] >> 7)
	}
	result[len(b)-1] = b[len(b)-1] << 1
	if msb != 0 {
		result[len(b)-1] ^= 0x87
	}
	return result
}

// omac computes OMAC^t_K(data): CMAC applied to [t]_n || data, where
// [t]_n is the block-sized big-endian representation of t.
func omac(block cipher.Block, t byte, data []byte) []byte {
	n := block.BlockSize()

	// L = E_K(0^n); Lu = 2L; Lu2 = 4L
	L := make([]byte, n)
	block.Encrypt(L, L)
	Lu := double(L)
	Lu2 := double(Lu)

	// Prepend the tweak block [t]_n to data.
	msg := make([]byte, n+len(data))
	msg[n-1] = t
	copy(msg[n:], data)

	// CBC-MAC over all blocks except the last.
	state := make([]byte, n)
	numFullBlocks := (len(msg) - 1) / n
	for i := 0; i < numFullBlocks; i++ {
		xorBytes(state, state, msg[i*n:(i+1)*n])
		block.Encrypt(state, state)
	}

	// Process the final block with the appropriate XOR mask.
	lastStart := numFullBlocks * n
	lastLen := len(msg) - lastStart
	last := make([]byte, n)
	copy(last, msg[lastStart:])
	if lastLen == n {
		xorBytes(last, last, Lu)
	} else {
		last[lastLen] = 0x80
		xorBytes(last, last, Lu2)
	}
	xorBytes(state, state, last)
	block.Encrypt(state, state)
	return state
}

// encrypt encrypts m with AES in EAX mode using key sk. It returns
// the ciphertext and random nonce. The EAX authentication tag is
// discarded, matching the original Python code.
func encrypt(m, sk []byte) (c, nonce []byte, err error) {
	block, err := aes.NewCipher(sk)
	if err != nil {
		return nil, nil, err
	}

	// 16-byte random nonce (PyCryptodome's default EAX nonce size).
	nonce = make([]byte, 16)
	if _, err = rand.Read(nonce); err != nil {
		return nil, nil, err
	}

	// Derive the CTR initial counter as OMAC^0(nonce).
	iv := omac(block, 0, nonce)

	// Encrypt in CTR mode.
	c = make([]byte, len(m))
	cipher.NewCTR(block, iv).XORKeyStream(c, m)
	return c, nonce, nil
}

// decrypt decrypts c with AES in EAX mode using key sk and the given
// nonce. No authentication is performed, matching the original Python
// code.
func decrypt(c, nonce, sk []byte) ([]byte, error) {
	block, err := aes.NewCipher(sk)
	if err != nil {
		return nil, err
	}

	// Derive the CTR initial counter as OMAC^0(nonce).
	iv := omac(block, 0, nonce)

	// Decrypt in CTR mode.
	m := make([]byte, len(c))
	cipher.NewCTR(block, iv).XORKeyStream(m, c)
	return m, nil
}