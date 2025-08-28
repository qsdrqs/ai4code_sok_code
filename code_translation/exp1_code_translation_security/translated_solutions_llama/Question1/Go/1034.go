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

// encodeStr encrypts a string using a provided key.
func encodeStr(plainText string, key string) (string, error) {
    // Generate a 32-byte key from the provided key
    keyHash := sha256.Sum256([]byte(key))
    keyBytes := keyHash[:]

    // Create a new AES cipher
    block, err := aes.NewCipher(keyBytes)
    if err != nil {
        return "", err
    }

    // Generate a random 12-byte nonce
    nonce := make([]byte, 12)
    if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
        return "", err
    }

    // Create a new GCM mode
    stream := cipher.NewCTR(block, nonce)

    // Pad the plaintext
    paddedPlainText := pad(plainText)

    // Encrypt the padded plaintext
    encrypted := make([]byte, len(paddedPlainText))
    stream.XORKeyStream(encrypted, []byte(paddedPlainText))

    // Prepend the nonce to the encrypted data
    encryptedData := append(nonce, encrypted...)

    // Base64 encode the encrypted data
    encodedStr := base64.StdEncoding.EncodeToString(encryptedData)

    return encodedStr, nil
}

// decodeStr decrypts a string using a provided key.
func decodeStr(encodedStr string, key string) (string, error) {
    // Generate a 32-byte key from the provided key
    keyHash := sha256.Sum256([]byte(key))
    keyBytes := keyHash[:]

    // Base64 decode the encoded string
    encodedData, err := base64.StdEncoding.DecodeString(encodedStr)
    if err != nil {
        return "", err
    }

    // Extract the nonce and encrypted data
    if len(encodedData) < 12 {
        return "", errors.New("invalid encoded data")
    }
    nonce := encodedData[:12]
    encrypted := encodedData[12:]

    // Create a new AES cipher
    block, err := aes.NewCipher(keyBytes)
    if err != nil {
        return "", err
    }

    // Create a new GCM mode
    stream := cipher.NewCTR(block, nonce)

    // Decrypt the encrypted data
    decrypted := make([]byte, len(encrypted))
    stream.XORKeyStream(decrypted, encrypted)

    // Unpad the decrypted data
    unpaddedDecrypted, err := unpad(decrypted)
    if err != nil {
        return "", err
    }

    return string(unpaddedDecrypted), nil
}

// pad pads a byte slice to a multiple of the AES block size (16 bytes).
func pad(s string) []byte {
    blockSize := 16
    paddingLen := blockSize - (len(s) % blockSize)
    padded := make([]byte, len(s)+paddingLen)
    copy(padded, s)
    for i := len(s); i < len(padded); i++ {
        padded[i] = byte(paddingLen)
    }
    return padded
}

// unpad unpads a byte slice.
func unpad(padded []byte) ([]byte, error) {
    if len(padded) == 0 {
        return nil, errors.New("empty padded data")
    }
    paddingLen := int(padded[len(padded)-1])
    if paddingLen < 1 || paddingLen > 16 {
        return nil, errors.New("invalid padding")
    }
    if len(padded) < paddingLen {
        return nil, errors.New("invalid padded data")
    }
    return padded[:len(padded)-paddingLen], nil
}

func main() {
    strEncoded1, err := encodeStr("I am okay", "wow")
    if err != nil {
        fmt.Println(err)
        return
    }
    strDecoded, err := decodeStr(strEncoded1, "wow")
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Println(strDecoded)
}