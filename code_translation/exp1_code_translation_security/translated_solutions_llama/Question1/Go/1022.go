package main

import (
    "crypto/rand"
    "encoding/base64"
    "fmt"
    "log"

    "github.com/fernet-go/fernet"
)

// generateKey generates a new Fernet key.
func generateKey() ([]byte, error) {
    // Fernet keys are URL-safe base64-encoded 32-byte strings.
    // We generate a random 32-byte key.
    key := make([]byte, 32)
    if _, err := rand.Read(key); err != nil {
        return nil, err
    }

    // URL-safe base64 encoding.
    return base64.URLEncoding.EncodeToString(key), nil
}

// encrypt encrypts the input string using the provided key.
func encrypt(inp string, key string) ([]byte, error) {
    f, err := fernet.NewFernet(key)
    if err != nil {
        return nil, err
    }
    return f.Encrypt([]byte(inp))
}

// decrypt decrypts the input bytes using the provided key.
func decrypt(inp []byte, key string) (string, error) {
    f, err := fernet.NewFernet(key)
    if err != nil {
        return "", err
    }
    decrypted, err := f.Decrypt(inp)
    if err != nil {
        return "", err
    }
    return string(decrypted), nil
}

func main() {
    // Generate a new Fernet key.
    exKey, err := generateKey()
    if err != nil {
        log.Fatal(err)
    }

    // Encrypt a message.
    encrypted, err := encrypt("Hello World!", string(exKey))
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println(encrypted)

    // Decrypt the message.
    decrypted, err := decrypt(encrypted, string(exKey))
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println(decrypted)
}