package main

import (
    "crypto/aes"
    "crypto/cipher"
    "crypto/rand"
    "crypto/sha256"
    "encoding/base64"
    "errors"
    "fmt"
    "io"
)

// AESCipher provides methods for encrypting and decrypting strings using AES.
type AESCipher struct {
    bs  int
    key []byte
}

// NewAESCipher returns a new AESCipher instance.
func NewAESCipher(key string) (*AESCipher, error) {
    hash := sha256.Sum256([]byte(key))
    return &AESCipher{
        bs:  32,
        key: hash[:],
    }, nil
}

// Encrypt encrypts a given string using the provided symmetric key.
func (a *AESCipher) Encrypt(raw string) ([]byte, error) {
    padded := a.pad(raw)
    iv := make([]byte, aes.BlockSize)
    if _, err := io.ReadFull(rand.Reader, iv); err != nil {
        return nil, err
    }
    block, err := aes.NewCipher(a.key)
    if err != nil {
        return nil, err
    }
    stream := cipher.NewCBC(block, iv)
    encrypted := make([]byte, len(padded))
    stream.Encrypt(encrypted, padded)
    return base64.StdEncoding.EncodeToString(append(iv, encrypted...)), nil
}

// Decrypt decrypts a given string using the provided symmetric key.
func (a *AESCipher) Decrypt(enc string) (string, error) {
    encrypted, err := base64.StdEncoding.DecodeString(enc)
    if err != nil {
        return "", err
    }
    if len(encrypted) < aes.BlockSize {
        return "", errors.New("encrypted data too short")
    }
    iv := encrypted[:aes.BlockSize]
    encrypted = encrypted[aes.BlockSize:]
    block, err := aes.NewCipher(a.key)
    if err != nil {
        return "", err
    }
    stream := cipher.NewCBC(block, iv)
    decrypted := make([]byte, len(encrypted))
    stream.Decrypt(decrypted, encrypted)
    unpadded, err := a.unpad(decrypted)
    if err != nil {
        return "", err
    }
    return string(unpadded), nil
}

func (a *AESCipher) pad(s string) []byte {
    paddingLen := a.bs - (len(s) % a.bs)
    padded := make([]byte, len(s)+paddingLen)
    copy(padded, s)
    for i := len(s); i < len(padded); i++ {
        padded[i] = byte(paddingLen)
    }
    return padded
}

func (a *AESCipher) unpad(s []byte) ([]byte, error) {
    if len(s) == 0 {
        return nil, errors.New("unpadded data empty")
    }
    paddingLen := int(s[len(s)-1])
    if paddingLen > len(s) || paddingLen < 1 || paddingLen > a.bs {
        return nil, errors.New("invalid padding")
    }
    return s[:len(s)-paddingLen], nil
}

func main() {
    key := "mysecretpassword"
    cipher, err := NewAESCipher(key)
    if err != nil {
        fmt.Println(err)
        return
    }
    encrypted, err := cipher.Encrypt("Secret Message A")
    if err != nil {
        fmt.Println(err)
        return
    }
    decrypted, err := cipher.Decrypt(encrypted)
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Printf("Encrypted: %s\n", encrypted)
    fmt.Printf("Decrypted: %s\n", decrypted)
}