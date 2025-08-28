package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/base64"
	"fmt"
	"log"
	"strings"
)

// NOTE: The original Python code uses AES in ECB (Electronic Codebook) mode, which is
// the default for PyCrypto's AES.new(key). ECB is insecure because it encrypts
// identical plaintext blocks into identical ciphertext blocks, revealing patterns
// in the data. It should NOT be used in production systems. This Go translation
// replicates the ECB behavior for direct compatibility with the Python example.
// In a real-world application, a secure mode like AES-GCM should be used.

// generateSecretKeyForAESCipher generates a random secret key for AES encryption.
// It returns the key as a Base64 encoded string.
func generateSecretKeyForAESCipher() (string, error) {
	// AES key length must be either 16, 24, or 32 bytes long.
	const aesKeyLength = 16 // Use a larger value like 32 in production for AES-256.

	// Generate a random secret key with the decided key length.
	// This secret key will be used to create an AES cipher for encryption/decryption.
	secretKey := make([]byte, aesKeyLength)
	if _, err := rand.Read(secretKey); err != nil {
		return "", fmt.Errorf("failed to generate random key: %w", err)
	}

	// Encode this secret key for storing safely in a database.
	encodedSecretKey := base64.StdEncoding.EncodeToString(secretKey)
	return encodedSecretKey, nil
}

// Function 1: encryptMessage encrypts a message using AES in ECB mode with custom padding.
func encryptMessage(privateMsg string, encodedSecretKey string, paddingCharacter string) (string, error) {
	// Decode the encoded secret key.
	secretKey, err := base64.StdEncoding.DecodeString(encodedSecretKey)
	if err != nil {
		return "", fmt.Errorf("failed to decode secret key: %w", err)
	}

	// Use the decoded secret key to create an AES cipher block.
	block, err := aes.NewCipher(secretKey)
	if err != nil {
		return "", fmt.Errorf("failed to create new AES cipher: %w", err)
	}

	// Pad the private_msg.
	// AES encryption requires the length of the msg to be a multiple of the block size (16).
	// This replicates the specific padding logic from the Python example.
	blockSize := block.BlockSize()
	padding := (blockSize - (len(privateMsg) % blockSize)) % blockSize
	padText := strings.Repeat(paddingCharacter, padding)
	paddedPrivateMsg := []byte(privateMsg + padText)

	// Use the cipher to encrypt the padded message (ECB mode).
	// Go's standard library requires manual implementation of ECB mode.
	ciphertext := make([]byte, len(paddedPrivateMsg))
	for bs, be := 0, blockSize; bs < len(paddedPrivateMsg); bs, be = bs+blockSize, be+blockSize {
		block.Encrypt(ciphertext[bs:be], paddedPrivateMsg[bs:be])
	}

	// Encode the encrypted msg for storing safely in the database.
	encodedEncryptedMsg := base64.StdEncoding.EncodeToString(ciphertext)

	// Return encoded encrypted message.
	return encodedEncryptedMsg, nil
}

// Function 2: decryptMessage decrypts a message using AES in ECB mode and removes custom padding.
func decryptMessage(encodedEncryptedMsg string, encodedSecretKey string, paddingCharacter string) (string, error) {
	// Decode the encoded encrypted message and encoded secret key.
	secretKey, err := base64.StdEncoding.DecodeString(encodedSecretKey)
	if err != nil {
		return "", fmt.Errorf("failed to decode secret key: %w", err)
	}
	encryptedMsg, err := base64.StdEncoding.DecodeString(encodedEncryptedMsg)
	if err != nil {
		return "", fmt.Errorf("failed to decode encrypted message: %w", err)
	}

	// Use the decoded secret key to create an AES cipher block.
	block, err := aes.NewCipher(secretKey)
	if err != nil {
		return "", fmt.Errorf("failed to create new AES cipher: %w", err)
	}

	// Check if the ciphertext is a multiple of the block size.
	if len(encryptedMsg)%block.BlockSize() != 0 {
		return "", fmt.Errorf("ciphertext is not a multiple of the block size")
	}

	// Use the cipher to decrypt the encrypted message (ECB mode).
	decryptedMsgBytes := make([]byte, len(encryptedMsg))
	for bs, be := 0, block.BlockSize(); bs < len(encryptedMsg); bs, be = bs+block.BlockSize, be+block.BlockSize {
		block.Decrypt(decryptedMsgBytes[bs:be], encryptedMsg[bs:be])
	}

	// Unpad the encrypted message by trimming the padding character from the right.
	unpaddedPrivateMsg := strings.TrimRight(string(decryptedMsgBytes), paddingCharacter)

	// Return a decrypted original private message.
	return unpaddedPrivateMsg, nil
}

func main() {
	####### BEGIN HERE #######

	privateMsg := `
 My test string
`
	paddingCharacter := "{"

	secretKey, err := generateSecretKeyForAESCipher()
	if err != nil {
		log.Fatalf("Error generating secret key: %v", err)
	}

	encryptedMsg, err := encryptMessage(privateMsg, secretKey, paddingCharacter)
	if err != nil {
		log.Fatalf("Error encrypting message: %v", err)
	}

	decryptedMsg, err := decryptMessage(encryptedMsg, secretKey, paddingCharacter)
	if err != nil {
		log.Fatalf("Error decrypting message: %v", err)
	}

	fmt.Printf("   Secret Key: %s - (%d)\n", secretKey, len(secretKey))
	fmt.Printf("Encrypted Msg: %s - (%d)\n", encryptedMsg, len(encryptedMsg))
	fmt.Printf("Decrypted Msg: %s - (%d)\n", decryptedMsg, len(decryptedMsg))
}