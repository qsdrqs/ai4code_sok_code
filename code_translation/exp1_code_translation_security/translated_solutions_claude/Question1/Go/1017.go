package main

import (
    "crypto/aes"
    "crypto/cipher"
    "crypto/rand"
    "errors"
    "io"
)

// CipherData holds the components needed for decryption
type CipherData struct {
    Nonce      []byte
    Tag        []byte
    Ciphertext []byte
}

// encrypt encrypts a given plaintext using a given symmetric key
func encrypt(key []byte, plaintext []byte) (*CipherData, error) {
    // Create AES cipher block
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Create GCM mode (equivalent to EAX mode for authenticated encryption)
    gcm, err := cipher.NewGCM(block)
    if err != nil {
        return nil, err
    }

    // Generate a random nonce
    nonce := make([]byte, gcm.NonceSize())
    if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
        return nil, err
    }

    // Encrypt and authenticate
    ciphertext := gcm.Seal(nil, nonce, plaintext, nil)
    
    // Split the result into ciphertext and tag
    tagSize := gcm.Overhead()
    actualCiphertext := ciphertext[:len(ciphertext)-tagSize]
    tag := ciphertext[len(ciphertext)-tagSize:]

    return &CipherData{
        Nonce:      nonce,
        Tag:        tag,
        Ciphertext: actualCiphertext,
    }, nil
}

// decrypt decrypts a given ciphertext using a given symmetric key
func decrypt(key []byte, cipherData *CipherData) ([]byte, error) {
    // Create AES cipher block
    block, err := aes.NewCipher(key)
    if err != nil {
        return nil, err
    }

    // Create GCM mode
    gcm, err := cipher.NewGCM(block)
    if err != nil {
        return nil, err
    }

    // Verify nonce size
    if len(cipherData.Nonce) != gcm.NonceSize() {
        return nil, errors.New("invalid nonce size")
    }

    // Reconstruct the sealed data (ciphertext + tag)
    sealedData := append(cipherData.Ciphertext, cipherData.Tag...)

    // Decrypt and verify
    plaintext, err := gcm.Open(nil, cipherData.Nonce, sealedData, nil)
    if err != nil {
        return nil, err
    }

    return plaintext, nil
}