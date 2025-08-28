package main

import (
    "crypto/aes"
    "crypto/cipher"
    "crypto/rand"
    "encoding/base64"
    "errors"
    "fmt"
    "io"
    "log"
    "os"
)

// GenerateKey generates a new encryption key.
func GenerateKey() ([]byte, error) {
    key := make([]byte, 32)
    _, err := io.ReadFull(rand.Reader, key)
    if err != nil {
        return nil, err
    }
    return key, nil
}

// Encrypt encrypts the given data with the provided key.
func Encrypt(data, key []byte) ([]byte, error) {
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Create a new GCM mode.
    gcm, err := cipher.NewGCM(block)
    if err != nil {
        return nil, err
    }

    // Generate a random nonce.
    nonce := make([]byte, gcm.NonceSize())
    _, err = io.ReadFull(rand.Reader, nonce)
    if err != nil {
        return nil, err
    }

    // Encrypt the data.
    ciphertext := gcm.Seal(nil, nonce, data, nil)

    // Prepend the nonce to the ciphertext.
    encrypted := make([]byte, 0, len(nonce)+len(ciphertext))
    encrypted = append(encrypted, nonce...)
    encrypted = append(encrypted, ciphertext...)

    return encrypted, nil
}

// Decrypt decrypts the given data with the provided key.
func Decrypt(encrypted, key []byte) ([]byte, error) {
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Create a new GCM mode.
    gcm, err := cipher.NewGCM(block)
    if err != nil {
        return nil, err
    }

    // Check if the encrypted data is long enough.
    if len(encrypted) < gcm.NonceSize() {
        return nil, errors.New("encrypted data too short")
    }

    // Extract the nonce.
    nonce := encrypted[:gcm.NonceSize()]

    // Extract the ciphertext.
    ciphertext := encrypted[gcm.NonceSize():]

    // Decrypt the data.
    decrypted, err := gcm.Open(nil, nonce, ciphertext, nil)
    if err != nil {
        return nil, err
    }

    return decrypted, nil
}

func main() {
    // Generate a new encryption key.
    key, err := GenerateKey()
    if err != nil {
        log.Fatal(err)
    }

    // Read the file to encrypt.
    filePath := "/safedir/test.txt"
    file, err := os.Open(filePath)
    if err != nil {
        log.Fatal(err)
    }
    defer file.Close()

    // Read the file contents.
    data, err := io.ReadAll(file)
    if err != nil {
        log.Fatal(err)
    }

    // Encrypt the file contents.
    encrypted, err := Encrypt(data, key)
    if err != nil {
        log.Fatal(err)
    }

    // Print the encrypted data.
    fmt.Println(base64.StdEncoding.EncodeToString(encrypted))

    // Save the key to a file for later decryption.
    keyFilePath := "/safedir/key.txt"
    err = os.WriteFile(keyFilePath, key, 0644)
    if err != nil {
        log.Fatal(err)
    }

    // Decrypt the data.
    decrypted, err := Decrypt(encrypted, key)
    if err != nil {
        log.Fatal(err)
    }

    // Print the decrypted data.
    fmt.Println(string(decrypted))
}

func main2() {
    // Decrypt using a saved key.
    keyFilePath := "/safedir/key.txt"
    key, err := os.ReadFile(keyFilePath)
    if err != nil {
        log.Fatal(err)
    }

    // Read the encrypted data.
    encryptedData := []byte("your_base64_encoded_encrypted_data")

    // Decode the base64 encoded encrypted data.
    encrypted, err := base64.StdEncoding.DecodeString(string(encryptedData))
    if err != nil {
        log.Fatal(err)
    }

    // Decrypt the data.
    decrypted, err := Decrypt(encrypted, key)
    if err != nil {
        log.Fatal(err)
    }

    // Print the decrypted data.
    fmt.Println(string(decrypted))
}