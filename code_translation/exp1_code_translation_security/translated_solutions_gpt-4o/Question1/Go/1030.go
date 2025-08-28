package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/base64"
	"fmt"
	"io"
)

func generateSecretKeyForAESCipher() string {
	// AES key length must be either 16, 24, or 32 bytes long
	AESKeyLength := 16 // use larger value in production
	// generate a random secret key with the decided key length
	secretKey := make([]byte, AESKeyLength)
	if _, err := io.ReadFull(rand.Reader, secretKey); err != nil {
		panic(err)
	}
	// encode this secret key for storing safely in database
	encodedSecretKey := base64.StdEncoding.EncodeToString(secretKey)
	return encodedSecretKey
}

func encryptMessage(privateMsg, encodedSecretKey, paddingCharacter string) string {
	// decode the encoded secret key
	secretKey, err := base64.StdEncoding.DecodeString(encodedSecretKey)
	if err != nil {
		panic(err)
	}
	// create a new AES cipher block
	block, err := aes.NewCipher(secretKey)
	if err != nil {
		panic(err)
	}

	// pad the privateMsg
	paddingSize := aes.BlockSize - len(privateMsg)%aes.BlockSize
	paddedPrivateMsg := privateMsg + string(make([]byte, paddingSize, paddingSize))

	// create a new GCM cipher mode
	ciphertext := make([]byte, aes.BlockSize+len(paddedPrivateMsg))
	iv := ciphertext[:aes.BlockSize]
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		panic(err)
	}

	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(ciphertext[aes.BlockSize:], []byte(paddedPrivateMsg))

	// encode the encrypted msg for storing safely in the database
	encodedEncryptedMsg := base64.StdEncoding.EncodeToString(ciphertext)
	return encodedEncryptedMsg
}

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

	// create a new AES cipher block
	block, err := aes.NewCipher(secretKey)
	if err != nil {
		panic(err)
	}

	// use the cipher to decrypt the encrypted message
	if len(encryptedMsg) < aes.BlockSize {
		panic("ciphertext too short")
	}
	iv := encryptedMsg[:aes.BlockSize]
	encryptedMsg = encryptedMsg[aes.BlockSize:]

	mode := cipher.NewCBCDecrypter(block, iv)
	mode.CryptBlocks(encryptedMsg, encryptedMsg)

	// unpad the encrypted message
	decryptedMsg := string(encryptedMsg)
	unpaddedPrivateMsg := decryptedMsg[:len(decryptedMsg)-int(decryptedMsg[len(decryptedMsg)-1])]

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