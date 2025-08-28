package main

import (
    "crypto/aes"
    "crypto/cipher"
    "encoding/binary"
    "errors"
    "fmt"
)

// AEScipher represents an AES cipher object
type AEScipher struct {
    key     []byte
    blkSize int
}

// NewAEScipher returns a new AEScipher object
func NewAEScipher(key []byte, blkSize int) (*AEScipher, error) {
    if len(key) != 16 && len(key) != 24 && len(key) != 32 {
        return nil, errors.New("invalid key size")
    }
    if blkSize != 16 {
        return nil, errors.New("invalid block size")
    }
    return &AEScipher{key: key, blkSize: blkSize}, nil
}

// Encrypt encrypts a message using AES ECB mode
func (a *AEScipher) Encrypt(msg []byte) ([]byte, error) {
    // Padding
    padLen := a.blkSize - (len(msg) % a.blkSize)
    paddedMsg := make([]byte, len(msg)+padLen)
    copy(paddedMsg, msg)
    for i := len(msg); i < len(paddedMsg); i++ {
        paddedMsg[i] = byte(padLen)
    }

    // Encryption
    block, err := aes.NewCipher(a.key)
    if err != nil {
        return nil, err
    }
    if block.Mode() != cipher.ECB {
        return nil, errors.New("only ECB mode is supported")
    }
    ciphertext := make([]byte, len(paddedMsg))
    for i := 0; i < len(paddedMsg); i += a.blkSize {
        block.Encrypt(ciphertext[i:i+a.blkSize], paddedMsg[i:i+a.blkSize])
    }
    return ciphertext, nil
}

// Decrypt decrypts a ciphertext using AES ECB mode
func (a *AEScipher) Decrypt(ciphertext []byte) ([]byte, error) {
    // Decryption
    block, err := aes.NewCipher(a.key)
    if err != nil {
        return nil, err
    }
    if block.Mode() != cipher.ECB {
        return nil, errors.New("only ECB mode is supported")
    }
    paddedMsg := make([]byte, len(ciphertext))
    for i := 0; i < len(ciphertext); i += a.blkSize {
        block.Decrypt(paddedMsg[i:i+a.blkSize], ciphertext[i:i+a.blkSize])
    }

    // Remove padding
    if len(paddedMsg) < a.blkSize {
        return nil, errors.New("invalid ciphertext")
    }
    padLen := int(paddedMsg[len(paddedMsg)-1])
    if padLen > a.blkSize || padLen < 0 {
        return nil, errors.New("invalid padding")
    }
    return paddedMsg[:len(paddedMsg)-padLen], nil
}

func main() {
    key := []byte{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15}
    blkSize := 16
    cipher, err := NewAEScipher(key, blkSize)
    if err != nil {
        fmt.Println(err)
        return
    }

    msg := []byte("Hello, World!")
    ciphertext, err := cipher.Encrypt(msg)
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Println("Ciphertext:", binary.Hex.EncodeToString(ciphertext))

    decryptedMsg, err := cipher.Decrypt(ciphertext)
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Println("Decrypted Message:", string(decryptedMsg))
}