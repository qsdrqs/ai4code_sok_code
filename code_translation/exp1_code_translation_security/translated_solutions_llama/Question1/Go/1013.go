package main

import (
    "crypto/rand"
    "crypto/sha256"
    "crypto/x509/pkix"
    "encoding/base64"
    "errors"
    "fmt"
    "golang.org/x/crypto/pbkdf2"
    "log"
)

// DeriveKey derives a secret key from a given password and salt
func deriveKey(password, salt []byte, iterations int) []byte {
    // Use PBKDF2 with SHA256 for key derivation
    key := pbkdf2.Key(password, salt, iterations, 32, sha256.New)
    return key
}

// Encrypt encrypts a message with a given password
func encrypt(message, password string, iterations int) ([]byte, error) {
    if iterations <= 0 {
        return nil, errors.New("iterations must be a positive integer")
    }

    // Generate a random salt
    salt := make([]byte, 16)
    if _, err := rand.Read(salt); err != nil {
        return nil, err
    }

    // Derive the key
    key := deriveKey([]byte(password), salt, iterations)

    // Create a new Fernet key
    fernetKey := base64.URLEncoding.EncodeToString(key)

    // Encrypt the message
    f, err := NewFernet(fernetKey)
    if err != nil {
        return nil, err
    }
    encryptedMessage, err := f.Encrypt([]byte(message))
    if err != nil {
        return nil, err
    }

    // Store the salt, iterations, and encrypted message
    iterBytes := make([]byte, 4)
    for i := 0; i < 4; i++ {
        iterBytes[i] = byte((iterations >> (8 * (3 - i))) & 0xFF)
    }

    result := append(salt, iterBytes...)
    result = append(result, encryptedMessage...)

    // Base64 encode the result
    encodedResult := base64.URLEncoding.EncodeToString(result)

    return []byte(encodedResult), nil
}

// Decrypt decrypts a token with a given password
func decrypt(token string, password string) ([]byte, error) {
    // Base64 decode the token
    decodedToken, err := base64.URLEncoding.DecodeString(token)
    if err != nil {
        return nil, err
    }

    // Extract the salt, iterations, and encrypted message
    if len(decodedToken) < 20 {
        return nil, errors.New("invalid token")
    }
    salt := decodedToken[:16]
    iterBytes := decodedToken[16:20]
    iterations := 0
    for i := 0; i < 4; i++ {
        iterations |= int(iterBytes[i]) << (8 * (3 - i))
    }
    encryptedMessage := decodedToken[20:]

    // Derive the key
    key := deriveKey([]byte(password), salt, iterations)

    // Create a new Fernet key
    fernetKey := base64.URLEncoding.EncodeToString(key)

    // Decrypt the message
    f, err := NewFernet(fernetKey)
    if err != nil {
        return nil, err
    }
    decryptedMessage, err := f.Decrypt(encryptedMessage)
    if err != nil {
        return nil, err
    }

    return decryptedMessage, nil
}

// fernetKey represents a URL-safe base64-encoded 32-byte key.
type fernetKey string

// fernet represents a Fernet symmetric encryption object.
type fernet struct {
    key fernetKey
}

// NewFernet returns a new Fernet object.
func NewFernet(key string) (*fernet, error) {
    if len(key) != 44 {
        return nil, errors.New("invalid key length")
    }
    return &fernet{fernetKey(key)}, nil
}

// Encrypt encrypts the given plaintext.
func (f *fernet) Encrypt(plaintext []byte) ([]byte, error) {
    // HMAC-SHA256
    mac := hmacSHA256(f.key, plaintext)
    // Version byte (1) + timestamp (8 bytes) + HMAC (32 bytes) + ciphertext
    ciphertext := make([]byte, 1+8+32+len(plaintext))
    ciphertext[0] = 1
    // Timestamp
    for i := 0; i < 8; i++ {
        ciphertext[1+i] = byte((int64(0) >> (8 * (7 - i))) & 0xFF)
    }
    // Copy HMAC
    copy(ciphertext[1+8:], mac)
    // Copy ciphertext
    copy(ciphertext[1+8+32:], plaintext)

    // AES-CBC
    iv := make([]byte, 16)
    if _, err := rand.Read(iv); err != nil {
        return nil, err
    }
    // Copy IV
    copy(ciphertext[1+8+32+len(plaintext):], iv)

    // Encrypt with AES-CBC
    block, err := aes.NewCipher(f.key)
    if err != nil {
        return nil, err
    }
    encrypted := make([]byte, len(plaintext)+16)
    stream := cbc.NewCBCEncrypter(block, iv)
    stream.CryptBlocks(encrypted, plaintext)

    // Copy encrypted data
    copy(ciphertext[1+8+32:], encrypted)

    return ciphertext, nil
}

// Decrypt decrypts the given ciphertext.
func (f *fernet) Decrypt(ciphertext []byte) ([]byte, error) {
    // Check version byte
    if ciphertext[0] != 1 {
        return nil, errors.New("invalid version")
    }

    // HMAC-SHA256
    mac := hmacSHA256(f.key, ciphertext[1+8+32:])

    // Check HMAC
    if !hmac.Equal(mac, ciphertext[1+8:1+8+32]) {
        return nil, errors.New("invalid HMAC")
    }

    // AES-CBC
    iv := ciphertext[1+8+32+len(ciphertext[1+8+32:]) : 1+8+32+len(ciphertext[1+8+32:])+16]

    // Decrypt with AES-CBC
    block, err := aes.NewCipher(f.key)
    if err != nil {
        return nil, err
    }
    decrypted := make([]byte, len(ciphertext[1+8+32:]) - 16)
    stream := cbc.NewCBCDecrypter(block, iv)
    stream.CryptBlocks(decrypted, ciphertext[1+8+32:])

    return decrypted, nil
}

func hmacSHA256(key fernetKey, data []byte) []byte {
    h := hmac.New(sha256.New, []byte(key))
    h.Write(data)
    return h.Sum(nil)
}

func main() {
    token, err := encrypt("nice123", "password123", 100000)
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println(string(token))

    decrypted, err := decrypt(string(token), "password123")
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println(string(decrypted))
}