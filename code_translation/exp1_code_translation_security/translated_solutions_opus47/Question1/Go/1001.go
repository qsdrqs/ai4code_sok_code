package main

func encrypt(key, s string) string {
    encryptedText := make([]byte, len(s))
    for i := 0; i < len(s); i++ {
        keyC := key[i%len(key)]
        encryptedText[i] = byte((int(s[i]) + int(keyC)) % 256)
    }
    return string(encryptedText)
}

func decrypt(key, s string) string {
    decryptedText := make([]byte, len(s))
    for i := 0; i < len(s); i++ {
        keyC := key[i%len(key)]
        decryptedText[i] = byte((int(s[i]) - int(keyC) + 256) % 256)
    }
    return string(decryptedText)
}