package main

import (
	"crypto/aes"
	"crypto/rand"
	"encoding/base64"
	"fmt"
	"strings"
)

func generateSecretKeyForAESCipher() string {
	// AES key length must be either 16, 24, or 32 bytes long
	AESKeyLength := 16 // use larger value in production
	// generate a random secret key with the decided key length
	// this secret key will be used to create AES cipher for encryption/decryption
	secretKey := make([]byte, AESKeyLength)
	_, err := rand.Read(secretKey)
	if err != nil {
		panic(err)
	}
	// encode this secret key for storing safely in database
	encodedSecretKey := base64.StdEncoding.EncodeToString(secretKey)
	return encodedSecretKey
}

// Function 1
func encryptMessage(privateMsg, encodedSecretKey, paddingCharacter string) string {
	// decode the encoded secret key
	secretKey, err := base64.StdEncoding.DecodeString(encodedSecretKey)
	if err != nil {
		panic(err)
	}
	// use the decoded secret key to create a AES cipher
	block, err := aes.NewCipher(secretKey)
	if err != nil {
		panic(err)
	}
	// pad the private_msg
	// because AES encryption requires the length of the msg to be a multiple of 16
	paddingLen := (aes.BlockSize - len(privateMsg)) % aes.BlockSize
	if paddingLen < 0 {
		paddingLen += aes.BlockSize // Go's % can yield negative; emulate Python's behavior
	}
	paddedPrivateMsg := privateMsg + strings.Repeat(paddingCharacter, paddingLen)
	// use the cipher to encrypt the padded message (ECB mode, like PyCrypto's default)
	paddedBytes := []byte(paddedPrivateMsg)
	encryptedMsg := make([]byte, len(paddedBytes))
	for i := 0; i < len(paddedBytes); i += aes.BlockSize {
		block.Encrypt(encryptedMsg[i:i+aes.BlockSize], paddedBytes[i:i+aes.BlockSize])
	}
	// encode the encrypted msg for storing safely in the database
	encodedEncryptedMsg := base64.StdEncoding.EncodeToString(encryptedMsg)
	// return encoded encrypted message
	return encodedEncryptedMsg
}

// Function 2
func decryptMessage(encodedEncryptedMsg, encodedSecretKey, paddingCharacter string) string {
	// decode the encoded encrypted message and encoded secret key
	secretKey, err := base64.StdEncoding.DecodeString(encodedSecretKey)
	if err != nil {
		panic(err)
	}
	encryptedMsg, err := base64.StdEncoding.DecodeString(encodedEncryptedMsg)
	if err != nil {
		panic(err)
	}
	// use the decoded secret key to create a AES cipher
	block, err := aes.NewCipher(secretKey)
	if err != nil {
		panic(err)
	}
	// use the cipher to decrypt the encrypted message (ECB mode, block by block)
	decryptedMsg := make([]byte, len(encryptedMsg))
	for i := 0; i < len(encryptedMsg); i += aes.BlockSize {
		block.Decrypt(decryptedMsg[i:i+aes.BlockSize], encryptedMsg[i:i+aes.BlockSize])
	}
	// convert decrypted message into a string
	decryptedMsgStr := string(decryptedMsg)
	// unpad the encrypted message
	unpaddedPrivateMsg := strings.TrimRight(decryptedMsgStr, paddingCharacter)
	// return a decrypted original private message
	return unpaddedPrivateMsg
}

/******* BEGIN HERE *******/

func main() {
	privateMsg := "\n My test string\n"
	paddingCharacter := "{"

	secretKey := generateSecretKeyForAESCipher()
	encryptedMsg := encryptMessage(privateMsg, secretKey, paddingCharacter)
	decryptedMsg := decryptMessage(encryptedMsg, secretKey, paddingCharacter)

	fmt.Printf("   Secret Key: %s - (%d)\n", secretKey, len(secretKey))
	fmt.Printf("Encrypted Msg: %s - (%d)\n", encryptedMsg, len(encryptedMsg))
	fmt.Printf("Decrypted Msg: %s - (%d)\n", decryptedMsg, len(decryptedMsg))
}