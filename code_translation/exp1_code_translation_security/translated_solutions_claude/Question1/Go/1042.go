package main

import (
    "fmt"
    "unicode"
)

func encrypt(key interface{}, str interface{}) *string {
    // Type checking for key
    keyInt, keyOk := key.(int)
    if !keyOk {
        return nil
    }
    
    // Type checking for string
    strString, strOk := str.(string)
    if !strOk {
        return nil
    }
    
    // Validation checks
    if keyInt < 0 {
        return nil
    }
    if len(strString) == 0 {
        return nil
    }
    
    // Handle key > 26
    if keyInt > 26 {
        keyInt = keyInt % 26
    }
    
    newString := ""
    for _, char := range strString {
        if unicode.IsLetter(char) {
            if unicode.IsLower(char) {
                newString += string(rune((int(char)-97+keyInt)%26 + 97))
            } else {
                newString += string(rune((int(char)-65+keyInt)%26 + 65))
            }
        } else {
            newString += string(char)
        }
    }
    
    return &newString
}

func decrypt(ciphertext interface{}, key interface{}) *string {
    // Type checking
    ciphertextStr, ciphertextOk := ciphertext.(string)
    if !ciphertextOk {
        return nil
    }
    
    keyStr, keyOk := key.(string)
    if !keyOk {
        return nil
    }
    
    // Validation checks
    if len(ciphertextStr) == 0 || len(keyStr) == 0 {
        return nil
    }
    
    if len(keyStr) == 1 {
        return &ciphertextStr
    }
    
    if len(keyStr) > len(ciphertextStr) {
        return nil
    }
    
    keyLen := len(keyStr)
    keyAscii := make([]int, keyLen)
    for i, char := range keyStr {
        keyAscii[i] = int(char)
    }
    
    ciphertextAscii := make([]int, len(ciphertextStr))
    for i, char := range ciphertextStr {
        ciphertextAscii[i] = int(char)
    }
    
    ciphertextLen := len(ciphertextAscii)
    plaintext := ""
    
    for i := 0; i < ciphertextLen; i++ {
        value := (ciphertextAscii[i] - keyAscii[i%keyLen]) % 26
        plaintext += string(rune(value + 65))
    }
    
    return &plaintext
}

func main() {
    // Example usage
    result1 := encrypt(3, "Hello World!")
    if result1 != nil {
        fmt.Println("Encrypted:", *result1)
    }
    
    result2 := decrypt("KHOOR", "ABC")
    if result2 != nil {
        fmt.Println("Decrypted:", *result2)
    }
}