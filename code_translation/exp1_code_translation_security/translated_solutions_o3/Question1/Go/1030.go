package main

import (
	"crypto/aes"
	"crypto/rand"
	"encoding/base64"
	"fmt"
	"io"
	"strings"
)

// AES key length must be either 16, 24, or 32 bytes long.
// We use 16 (AES-128) just like the Python example.
const aesKeyLength = 16

// ---------------------------------------------------------------------
// Key generation
// ---------------------------------------------------------------------
func generateSecretKeyForAESCipher() string {
	key := make([]byte, aesKeyLength)
	_, err := io.ReadFull(rand.Reader, key)
	if err != nil {
		panic(err)
	}
	// Return the key base-64 encoded ( []byte -> string )
	return base64.StdEncoding.EncodeToString(key)
}

// ---------------------------------------------------------------------
// AES-ECB helpers (PyCrypto's default mode)
// ---------------------------------------------------------------------
func encryptECB(block cipherBlock, plaintext []byte) []byte {
	if len(plaintext)%block.BlockSize() != 0 {
		panic("plaintext is not a multiple of the block size")
	}
	ciphertext := make([]byte, len(plaintext))
	for bs, be := 0, block.BlockSize(); bs < len(plaintext); bs, be = bs+block.BlockSize(), be+block.BlockSize() {
		block.Encrypt(ciphertext[bs:be], plaintext[bs:be])
	}
	return ciphertext
}

func decryptECB(block cipherBlock, ciphertext []byte) []byte {
	if len(ciphertext)%block.BlockSize() != 0 {
		panic("ciphertext is not a multiple of the block size")
	}
	plaintext := make([]byte, len(ciphertext))
	for bs, be := 0, block.BlockSize(); bs < len(ciphertext); bs, be = bs+block.BlockSize(), be+block.BlockSize() {
		block.Decrypt(plaintext[bs:be], ciphertext[bs:be])
	}
	return plaintext
}

// ---------------------------------------------------------------------
// Encryption (Function 1) and decryption (Function 2)
// ---------------------------------------------------------------------
func encryptMessage(privateMsg, encodedSecretKey string, paddingCharacter rune) string {
	// Decode key from base64
	secretKey, err := base64.StdEncoding.DecodeString(encodedSecretKey)
	if err != nil {
		panic(err)
	}

	// Create AES cipher block
	block, err := aes.NewCipher(secretKey)
	if err != nil {
		panic(err)
	}

	// Pad the message (makes length a multiple of 16)
	blockSize := block.BlockSize()
	padLen := (blockSize - (len(privateMsg) % blockSize)) % blockSize
	paddedMsg := privateMsg + strings.Repeat(string(paddingCharacter), padLen)

	// Encrypt using ECB mode
	ciphertext := encryptECB(block, []byte(paddedMsg))

	// Return base64 representation of ciphertext
	return base64.StdEncoding.EncodeToString(ciphertext)
}

func decryptMessage(encodedEncryptedMsg, encodedSecretKey string, paddingCharacter rune) string {
	// Decode key and ciphertext from base64
	secretKey, err := base64.StdEncoding.DecodeString(encodedSecretKey)
	if err != nil {
		panic(err)
	}
	encryptedMsg, err := base64.StdEncoding.DecodeString(encodedEncryptedMsg)
	if err != nil {
		panic(err)
	}

	block, err := aes.NewCipher(secretKey)
	if err != nil {
		panic(err)
	}

	// Decrypt using ECB mode
	decrypted := decryptECB(block, encryptedMsg)

	// Remove padding
	unpadded := strings.TrimRight(string(decrypted), string(paddingCharacter))

	return unpadded
}

// ---------------------------------------------------------------------
// Main – replicates the Python test harness
// ---------------------------------------------------------------------
func main() {
	privateMsg := `
 My test string
`
	paddingCharacter := '{'

	secretKey := generateSecretKeyForAESCipher()
	encryptedMsg := encryptMessage(privateMsg, secretKey, paddingCharacter)
	decryptedMsg := decryptMessage(encryptedMsg, secretKey, paddingCharacter)

	fmt.Printf("   Secret Key: %s - (%d)\n", secretKey, len(secretKey))
	fmt.Printf("Encrypted Msg: %s - (%d)\n", encryptedMsg, len(encryptedMsg))
	fmt.Printf("Decrypted Msg: %s - (%d)\n", decryptedMsg, len(decryptedMsg))
}