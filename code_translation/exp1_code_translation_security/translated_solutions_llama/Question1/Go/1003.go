package main

import (
    "crypto/aes"
    "crypto/cipher"
    "errors"
    "fmt"
)

// EncryptBytesAES encrypts the given data using AES in ECB mode with the provided key.
func EncryptBytesAES(data, key []byte) ([]byte, error) {
    // Check if the key is a valid AES key size
    if len(key) != 16 && len(key) != 24 && len(key) != 32 {
        return nil, errors.New("invalid key size")
    }

    // Create a new AES cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Since AES.MODE_ECB is not directly available in Go, we use the Stream method
    // to create an ECB mode stream
    stream := cipher.NewECBEncrypter(block)

    // The input must be a multiple of the block size
    if len(data)%16 != 0 {
        paddedData := make([]byte, len(data)+16-(len(data)%16))
        copy(paddedData, data)
        data = paddedData
    }

    // Encrypt the data
    encryptedData := make([]byte, len(data))
    stream.CryptBlocks(encryptedData, data)

    return encryptedData, nil
}

// DecryptBytesAES decrypts the given data using AES in ECB mode with the provided key.
func DecryptBytesAES(data, key []byte) ([]byte, error) {
    // Check if the key is a valid AES key size
    if len(key) != 16 && len(key) != 24 && len(key) != 32 {
        return nil, errors.New("invalid key size")
    }

    // Create a new AES cipher
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Since AES.MODE_ECB is not directly available in Go, we use the Stream method
    // to create an ECB mode stream
    stream := cipher.NewECBDecrypter(block)

    // Decrypt the data
    decryptedData := make([]byte, len(data))
    stream.CryptBlocks(decryptedData, data)

    // Remove padding
    for i := len(decryptedData) - 1; i >= 0; i-- {
        if decryptedData[i] != 0 {
            return decryptedData[:i+1], nil
        }
    }

    return decryptedData, nil
}

func main() {
    key := []byte{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15}
    data := []byte{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15}

    encryptedData, err := EncryptBytesAES(data, key)
    if err != nil {
        fmt.Println(err)
        return
    }

    fmt.Println("Encrypted Data:", encryptedData)

    decryptedData, err := DecryptBytesAES(encryptedData, key)
    if err != nil {
        fmt.Println(err)
        return
    }

    fmt.Println("Decrypted Data:", decryptedData)
}