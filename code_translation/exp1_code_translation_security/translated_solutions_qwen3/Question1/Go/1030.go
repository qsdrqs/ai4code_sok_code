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

func generateSecretKeyForAES() (string, error) {
	key := make([]byte, 16)
	_, err := rand.Read(key)
	if err != nil {
		return "", err
	}
	return base64.StdEncoding.EncodeToString(key), nil
}

func encryptMessage(privateMsg, encodedSecretKey, paddingCharacter string) (string, error) {
	key, err := base64.StdEncoding.DecodeString(encodedSecretKey)
	if err != nil {
		return "", err
	}

	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	paddingLen := (16 - (len(privateMsg) % 16)) % 16
	paddedMsg := privateMsg + strings.Repeat(paddingCharacter, paddingLen)

	plaintext := []byte(paddedMsg)
	ciphertext := make([]byte, len(plaintext))

	mode := newECBEncrypter(block)
	mode.CryptBlocks(ciphertext, plaintext)

	return base64.StdEncoding.EncodeToString(ciphertext), nil
}

func decryptMessage(encodedEncryptedMsg, encodedSecretKey, paddingCharacter string) (string, error) {
	ciphertext, err := base64.StdEncoding.DecodeString(encodedEncryptedMsg)
	if err != nil {
		return "", err
	}

	key, err := base64.StdEncoding.DecodeString(encodedSecretKey)
	if err != nil {
		return "", err
	}

	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	decrypted := make([]byte, len(ciphertext))
	mode := newECBDecrypter(block)
	mode.CryptBlocks(decrypted, ciphertext)

	unpaddedMsg := strings.TrimRight(string(decrypted), paddingCharacter)
	return unpaddedMsg, nil
}

type ecbEncrypter struct {
	b cipher.Block
}

func newECBEncrypter(b cipher.Block) cipher.BlockMode {
	return &ecbEncrypter{b: b}
}

func (e *ecbEncrypter) BlockSize() int {
	return e.b.BlockSize()
}

func (e *ecbEncrypter) CryptBlocks(dst, src []byte) {
	if len(src) == 0 {
		return
	}
	if len(src)%e.BlockSize() != 0 {
		panic("crypto/cipher: input not full blocks")
	}
	if len(dst) < len(src) {
		panic("crypto/cipher: output smaller than input")
	}
	for i := 0; i < len(src); i += e.b.BlockSize() {
		e.b.Encrypt(dst[i:], src[i:])
	}
}

type ecbDecrypter struct {
	b cipher.Block
}

func newECBDecrypter(b cipher.Block) cipher.BlockMode {
	return &ecbDecrypter{b: b}
}

func (d *ecbDecrypter) BlockSize() int {
	return d.b.BlockSize()
}

func (d *ecbDecrypter) CryptBlocks(dst, src []byte) {
	if len(src) == 0 {
		return
	}
	if len(src)%d.BlockSize() != 0 {
		panic("crypto/cipher: input not full blocks")
	}
	if len(dst) < len(src) {
		panic("crypto/cipher: output smaller than input")
	}
	for i := 0; i < len(src); i += d.b.BlockSize() {
		d.b.Decrypt(dst[i:], src[i:])
	}
}

func main() {
	privateMsg := "\n My test string\n"
	paddingCharacter := "{"

	secretKey, err := generateSecretKeyForAES()
	if err != nil {
		log.Fatal(err)
	}

	encryptedMsg, err := encryptMessage(privateMsg, secretKey, paddingCharacter)
	if err != nil {
		log.Fatal(err)
	}

	decryptedMsg, err := decryptMessage(encryptedMsg, secretKey, paddingCharacter)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Printf("   Secret Key: %s - (%d)\n", secretKey, len(secretKey))
	fmt.Printf("Encrypted Msg: %s - (%d)\n", encryptedMsg, len(encryptedMsg))
	fmt.Printf("Decrypted Msg: %s - (%d)\n", decryptedMsg, len(decryptedMsg))
}