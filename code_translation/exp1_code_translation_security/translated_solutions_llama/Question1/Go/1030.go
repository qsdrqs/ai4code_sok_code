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
)

// generateSecretKeyForAESCipher generates a random secret key for AES encryption.
func generateSecretKeyForAESCipher() ([]byte, error) {
    // AES key length must be either 16, 24, or 32 bytes long
    aesKeyLength := 16 // use larger value in production
    secretKey := make([]byte, aesKeyLength)
    _, err := io.ReadFull(rand.Reader, secretKey)
    if err != nil {
        return nil, err
    }
    // encode this secret key for storing safely in database
    encodedSecretKey := base64.StdEncoding.EncodeToString(secretKey)
    return []byte(encodedSecretKey), nil
}

// encryptMessage encrypts a private message using AES encryption.
func encryptMessage(privateMsg string, encodedSecretKey string, paddingCharacter string) ([]byte, error) {
    // decode the encoded secret key
    secretKey, err := base64.StdEncoding.DecodeString(encodedSecretKey)
    if err != nil {
        return nil, err
    }
    // use the decoded secret key to create a AES cipher
    block, err := aes.NewCipher(secretKey)
    if err != nil {
        return nil, err
    }
    // pad the private_msg
    // because AES encryption requires the length of the msg to be a multiple of 16
    paddedPrivateMsg := pad(privateMsg, block.BlockSize(), paddingCharacter)
    // use the cipher to encrypt the padded message
    encryptedMsg := make([]byte, block.BlockSize()+len(paddedPrivateMsg))
    iv := make([]byte, block.BlockSize())
    if _, err := io.ReadFull(rand.Reader, iv); err != nil {
        return nil, err
    }
    copy(encryptedMsg, iv)
    stream := cipher.NewCFBEncrypter(block, iv)
    stream.XORKeyStream(encryptedMsg[block.BlockSize():], []byte(paddedPrivateMsg))
    // encode the encrypted msg for storing safely in the database
    encodedEncryptedMsg := base64.StdEncoding.EncodeToString(encryptedMsg)
    // return encoded encrypted message
    return []byte(encodedEncryptedMsg), nil
}

// decryptMessage decrypts an encrypted message using AES decryption.
func decryptMessage(encodedEncryptedMsg string, encodedSecretKey string, paddingCharacter string) (string, error) {
    // decode the encoded encrypted message and encoded secret key
    encryptedMsg, err := base64.StdEncoding.DecodeString(encodedEncryptedMsg)
    if err != nil {
        return "", err
    }
    secretKey, err := base64.StdEncoding.DecodeString(encodedSecretKey)
    if err != nil {
        return "", err
    }
    // use the decoded secret key to create a AES cipher
    block, err := aes.NewCipher(secretKey)
    if err != nil {
        return "", err
    }
    // use the cipher to decrypt the encrypted message
    iv := encryptedMsg[:block.BlockSize()]
    encryptedMsg = encryptedMsg[block.BlockSize():]
    stream := cipher.NewCFBDecrypter(block, iv)
    decryptedMsg := make([]byte, len(encryptedMsg))
    stream.XORKeyStream(decryptedMsg, encryptedMsg)
    // unpad the encrypted message
    unpaddedPrivateMsg := unpad(string(decryptedMsg), block.BlockSize(), paddingCharacter)
    // return a decrypted original private message
    return unpaddedPrivateMsg, nil
}

func pad(s string, blockSize int, paddingCharacter string) string {
    paddingLength := blockSize - (len(s) % blockSize)
    return s + strings.Repeat(paddingCharacter, paddingLength)
}

func unpad(s string, blockSize int, paddingCharacter string) (string, error) {
    paddingLength := len(s) % blockSize
    if paddingLength == 0 {
        return "", errors.New("invalid padding")
    }
    padding := s[len(s)-paddingLength:]
    for i := 0; i < paddingLength; i++ {
        if padding[i] != paddingCharacter[0] {
            return "", errors.New("invalid padding")
        }
    }
    return s[:len(s)-paddingLength], nil
}

func stringsRepeat(s string, count int) string {
    var r string
    for i := 0; i < count; i++ {
        r += s
    }
    return r
}

func main() {
    privateMsg := `
 My test string
`
    paddingCharacter := "{"

    secretKey, err := generateSecretKeyForAESCipher()
    if err != nil {
        log.Fatal(err)
    }
    encryptedMsg, err := encryptMessage(privateMsg, string(secretKey), paddingCharacter)
    if err != nil {
        log.Fatal(err)
    }
    decryptedMsg, err := decryptMessage(string(encryptedMsg), string(secretKey), paddingCharacter)
    if err != nil {
        log.Fatal(err)
    }

    fmt.Printf("   Secret Key: %s - (%d)\n", secretKey, len(secretKey))
    fmt.Printf("Encrypted Msg: %s - (%d)\n", encryptedMsg, len(encryptedMsg))
    fmt.Printf("Decrypted Msg: %s - (%d)\n", decryptedMsg, len(decryptedMsg))
}