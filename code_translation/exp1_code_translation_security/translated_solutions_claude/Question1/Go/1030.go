package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/base64"
	"fmt"
	"strings"
)

func generateSecretKeyForAESCipher() []byte {
	// AES key length must be either 16, 24, or 32 bytes long
	AESKeyLength := 16 // use larger value in production
	// generate a random secret key with the decided key length
	// this secret key will be used to create AES cipher for encryption/decryption
	secretKey := make([]byte, AESKeyLength)
	rand.Read(secretKey)
	// encode this secret key for storing safely in database
	encodedSecretKey := base64.StdEncoding.EncodeToString(secretKey)
	return []byte(encodedSecretKey)
}

// Function 1
func encryptMessage(privateMsg string, encodedSecretKey []byte, paddingCharacter string) []byte {
	// decode the encoded secret key
	secretKey, _ := base64.StdEncoding.DecodeString(string(encodedSecretKey))
	// use the decoded secret key to create a AES cipher
	block, _ := aes.NewCipher(secretKey)
	// pad the private_msg
	// because AES encryption requires the length of the msg to be a multiple of 16
	paddingLength := (16 - len(privateMsg)%16) % 16
	paddedPrivateMsg := privateMsg + strings.Repeat(paddingCharacter, paddingLength)
	
	// use ECB mode (similar to Python's default AES.new behavior)
	encrypted := make([]byte, len(paddedPrivateMsg))
	for i := 0; i < len(paddedPrivateMsg); i += 16 {
		block.Encrypt(encrypted[i:i+16], []byte(paddedPrivateMsg[i:i+16]))
	}
	
	// encode the encrypted msg for storing safely in the database
	encodedEncryptedMsg := base64.StdEncoding.EncodeToString(encrypted)
	// return encoded encrypted message
	return []byte(encodedEncryptedMsg)
}

// Function 2
func decryptMessage(encodedEncryptedMsg []byte, encodedSecretKey []byte, paddingCharacter string) string {
	// decode the encoded encrypted message and encoded secret key
	secretKey, _ := base64.StdEncoding.DecodeString(string(encodedSecretKey))
	encryptedMsg, _ := base64.StdEncoding.DecodeString(string(encodedEncryptedMsg))
	
	// use the decoded secret key to create a AES cipher
	block, _ := aes.NewCipher(secretKey)
	
	// use ECB mode to decrypt
	decrypted := make([]byte, len(encryptedMsg))
	for i := 0; i < len(encryptedMsg); i += 16 {
		block.Decrypt(decrypted[i:i+16], encryptedMsg[i:i+16])
	}
	
	// convert decrypted message into a string
	decryptedMsg := string(decrypted)
	// unpad the encrypted message
	unpaddedPrivateMsg := strings.TrimRight(decryptedMsg, paddingCharacter)
	// return a decrypted original private message
	return unpaddedPrivateMsg
}

func main() {
	privateMsg := `
 My test string
`
	paddingCharacter := "{"

	secretKey := generateSecretKeyForAESCipher()
	encryptedMsg := encryptMessage(privateMsg, secretKey, paddingCharacter)
	decryptedMsg := decryptMessage(encryptedMsg, secretKey, paddingCharacter)

	fmt.Printf("   Secret Key: %s - (%d)\n", secretKey, len(secretKey))
	fmt.Printf("Encrypted Msg: %s - (%d)\n", encryptedMsg, len(encryptedMsg))
	fmt.Printf("Decrypted Msg: %s - (%d)\n", decryptedMsg, len(decryptedMsg))
}