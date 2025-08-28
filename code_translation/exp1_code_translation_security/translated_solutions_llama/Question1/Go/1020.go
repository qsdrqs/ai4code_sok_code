package main

import (
    "crypto/aes"
    "crypto/cipher"
    "crypto/rand"
    "encoding/base64"
    "errors"
    "fmt"
    "io"
)

// pad adds PKCS#7 padding to the given plaintext
func pad(plaintext []byte) []byte {
    blockSize := aes.BlockSize
    paddingLen := blockSize - (len(plaintext) % blockSize)
    padding := make([]byte, paddingLen)
    for i := range padding {
        padding[i] = byte(paddingLen)
    }
    return append(plaintext, padding...)
}

// unpad removes PKCS#7 padding from the given ciphertext
func unpad(ciphertext []byte) ([]byte, error) {
    if len(ciphertext) == 0 {
        return nil, errors.New("ciphertext is empty")
    }
    blockSize := aes.BlockSize
    paddingLen := int(ciphertext[len(ciphertext)-1])
    if paddingLen > blockSize || paddingLen == 0 {
        return nil, errors.New("invalid padding")
    }
    if len(ciphertext) < paddingLen {
        return nil, errors.New("ciphertext is too short")
    }
    return ciphertext[:len(ciphertext)-paddingLen], nil
}

// encrypt encrypts the given plaintext using the provided key
func encrypt(key []byte, plaintext []byte) ([]byte, error) {
    if len(key) != 16 && len(key) != 24 && len(key) != 32 {
        return nil, errors.New("invalid key size")
    }

    // Generate a random 128-bit IV.
    iv := make([]byte, 16)
    if _, err := io.ReadFull(rand.Reader, iv); err != nil {
        return nil, err
    }

    // Create a new AES cipher.
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Create a CBC mode.
    cbc := cipher.NewCBCEncrypter(block, iv)

    // Pad the plaintext.
    paddedTxt := pad(plaintext)

    // Encrypt the padded plaintext.
    cipherTxt := make([]byte, cbc.BlockSize()+len(paddedTxt))
    cbc.CryptBlocks(cipherTxt[16:], paddedTxt)

    // Prepend the IV to the ciphertext and base64 encode it.
    cipherTxt = append(iv, cipherTxt[16:]...)
    return base64.StdEncoding.EncodeToString(cipherTxt), nil
}

// decrypt decrypts the given ciphertext using the provided key
func decrypt(key []byte, ciphertext string) ([]byte, error) {
    if len(key) != 16 && len(key) != 24 && len(key) != 32 {
        return nil, errors.New("invalid key size")
    }

    // Base64 decode the ciphertext.
    cipherTxt, err := base64.StdEncoding.DecodeString(ciphertext)
    if err != nil {
        return nil, err
    }

    // Extract the IV from the ciphertext.
    if len(cipherTxt) < 16 {
        return nil, errors.New("ciphertext is too short")
    }
    iv := cipherTxt[:16]
    cipherTxt = cipherTxt[16:]

    // Create a new AES cipher.
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Create a CBC mode.
    cbc := cipher.NewCBCDecrypter(block, iv)

    // Decrypt the ciphertext.
    paddedTxt := make([]byte, len(cipherTxt))
    cbc.CryptBlocks(paddedTxt, cipherTxt)

    // Unpad the decrypted text.
    return unpad(paddedTxt)
}

func main() {
    key := []byte("0123456789abcdef") // 128-bit key
    plaintext := []byte("Hello, World!")
    fmt.Println("Plaintext:", string(plaintext))

    // Encrypt the plaintext.
    cipherTxt, err := encrypt(key, plaintext)
    if err != nil {
        fmt.Println("Error encrypting:", err)
        return
    }
    fmt.Println("Ciphertext:", cipherTxt)

    // Decrypt the ciphertext.
    decryptedTxt, err := decrypt(key, cipherTxt)
    if err != nil {
        fmt.Println("Error decrypting:", err)
        return
    }
    fmt.Println("Decrypted text:", string(decryptedTxt))
}