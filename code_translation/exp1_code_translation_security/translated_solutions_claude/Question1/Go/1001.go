package main

import (
    "strings"
)

func encrypt(key string, text string) string {
    var encryptedText strings.Builder
    keyLen := len(key)
    
    for i, char := range text {
        keyChar := key[i % keyLen]
        encryptedChar := byte((int(char) + int(keyChar)) % 256)
        encryptedText.WriteByte(encryptedChar)
    }
    
    return encryptedText.String()
}

func decrypt(key string, text string) string {
    var decryptedText strings.Builder
    keyLen := len(key)
    
    for i, char := range text {
        keyChar := key[i % keyLen]
        decryptedChar := byte((int(char) - int(keyChar) + 256) % 256)
        decryptedText.WriteByte(decryptedChar)
    }
    
    return decryptedText.String()
}