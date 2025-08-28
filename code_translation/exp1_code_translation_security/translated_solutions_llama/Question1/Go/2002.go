package main

import (
    "crypto/aes"
    "crypto/cipher"
    "crypto/rand"
    "encoding/utf-8"
    "errors"
    "fmt"
    "io"
)

// encrypt encrypts a given message using AES in CTR mode.
func encrypt(k []byte, m string) ([]byte, error) {
    // Create a new AES cipher.
    block, err := aes.NewCipher(k)
    if err != nil {
        return nil, err
    }

    // Create a new CTR mode.
    iv := make([]byte, block.BlockSize())
    if _, err := io.ReadFull(rand.Reader, iv); err != nil {
        return nil, err
    }

    // Prepend the IV to the ciphertext.
    stream := cipher.NewCTR(block, iv)
    ct := make([]byte, len(iv)+len(m))
    copy(ct, iv)

    // Encrypt the message.
    stream.XORKeyStream(ct[len(iv):], []byte(m))
    return ct, nil
}

// decrypt decrypts a given ciphertext using AES in CTR mode.
func decrypt(k []byte, ct []byte) (string, error) {
    // Check if the ciphertext is at least as long as the IV.
    block, err := aes.NewCipher(k)
    if err != nil {
        return "", err
    }

    if len(ct) < block.BlockSize() {
        return "", errors.New("ciphertext is too short")
    }

    // Extract the IV from the ciphertext.
    iv := ct[:block.BlockSize()]

    // Create a new CTR mode.
    stream := cipher.NewCTR(block, iv)

    // Decrypt the message.
    pt := make([]byte, len(ct)-block.BlockSize())
    stream.XORKeyStream(pt, ct[block.BlockSize():])

    return string(pt), nil
}

func test() {
    roundTripTest := func() {
        k := []byte("This_key_for_demo_purposes_only!")
        m := "Text may be any length you wish, no padding is required"

        ct, err := encrypt(k, m)
        if err != nil {
            fmt.Println(err)
            return
        }

        pt, err := decrypt(k, ct)
        if err != nil {
            fmt.Println(err)
            return
        }

        fmt.Println(pt)
    }

    roundTripTest()
}

func main() {
    test()
}