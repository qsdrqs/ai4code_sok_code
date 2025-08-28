package main

import (
    "crypto/aes"
    "crypto/cipher"
    "encoding/hex"
    "errors"
    "fmt"
)

// Encode encodes a byte slice using an AES key in ECB mode.
func Encode(inp, key []byte) ([]byte, error) {
    // Check if the key is a multiple of the AES block size (16 bytes)
    if len(key) != 16 {
        return nil, errors.New("key must be 16 bytes long")
    }

    // Create a new AES cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Since we're using ECB mode, we can directly use the cipher to encrypt the input
    // However, ECB mode is not recommended for encrypting large amounts of data or data that needs to be secure
    // because it doesn't use an initialization vector (IV) and is therefore vulnerable to certain attacks
    // For real-world applications, consider using a mode like CBC or GCM

    // Pad the input to a multiple of the block size
    padded := pad(inp, block.BlockSize())

    // Create a new ECB mode
    ecb := cipher.NewECBEncrypter(block)

    // Encrypt the padded input
    encrypted := make([]byte, len(padded))
    ecb.CryptBlocks(encrypted, padded)

    return encrypted, nil
}

// Decode decodes a byte slice using an AES key in ECB mode.
func Decode(inp, key []byte) ([]byte, error) {
    // Check if the key is a multiple of the AES block size (16 bytes)
    if len(key) != 16 {
        return nil, errors.New("key must be 16 bytes long")
    }

    // Create a new AES cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Create a new ECB mode
    ecb := cipher.NewECBDecrypter(block)

    // Decrypt the input
    decrypted := make([]byte, len(inp))
    ecb.CryptBlocks(decrypted, inp)

    // Unpad the decrypted input
    unpadded, err := unpad(decrypted, block.BlockSize())
    if err != nil {
        return nil, err
    }

    return unpadded, nil
}

// pad pads a byte slice to a multiple of a given block size using PKCS#7 padding.
func pad(b []byte, blockSize int) []byte {
    paddingLen := blockSize - (len(b) % blockSize)
    padding := make([]byte, paddingLen)
    for i := range padding {
        padding[i] = byte(paddingLen)
    }
    return append(b, padding...)
}

// unpad removes PKCS#7 padding from a byte slice.
func unpad(b []byte, blockSize int) ([]byte, error) {
    if len(b) == 0 || len(b)%blockSize != 0 {
        return nil, errors.New("invalid padding")
    }
    paddingLen := int(b[len(b)-1])
    if paddingLen > blockSize || paddingLen == 0 {
        return nil, errors.New("invalid padding")
    }
    for i := 1; i <= paddingLen; i++ {
        if b[len(b)-i] != byte(paddingLen) {
            return nil, errors.New("invalid padding")
        }
    }
    return b[:len(b)-paddingLen], nil
}

func main() {
    inp := []byte("1111111111111111")
    key := []byte("1111111111111111")

    encoded, err := Encode(inp, key)
    if err != nil {
        fmt.Println(err)
        return
    }

    decoded, err := Decode(encoded, key)
    if err != nil {
        fmt.Println(err)
        return
    }

    if string(decoded) != string(inp) {
        fmt.Println("Decoded input does not match original input")
    } else {
        fmt.Println("Decoded input matches original input")
    }
}