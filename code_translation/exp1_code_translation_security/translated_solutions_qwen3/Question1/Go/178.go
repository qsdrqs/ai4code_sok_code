package main

import "fmt"

func encrypt(str string, key int) string {
    alpha := []rune("abcdefghijklmnopqrstuvwxyz")
    newString := []rune{}
    for _, c := range str {
        if c == ' ' {
            newString = append(newString, ' ')
            continue
        }
        alphaIndex := -1
        for i, r := range alpha {
            if r == c {
                alphaIndex = i
                break
            }
        }
        if alphaIndex+key > 25 {
            alphaIndex = key - (25 - alphaIndex)
        }
        newString = append(newString, alpha[alphaIndex+key])
    }
    return string(newString)
}

func decrypt(str string, key int) string {
    alpha := []rune("abcdefghijklmnopqrstuvwxyz")
    newString := []rune{}
    for _, c := range str {
        if c == ' ' {
            continue
        }
        alphaIndex := -1
        for i, r := range alpha {
            if r == c {
                alphaIndex = i
                break
            }
        }
        newString = append(newString, alpha[alphaIndex-key])
    }
    return string(newString)
}

func main() {
    encrypted := encrypt("hello worlz", 3)
    fmt.Println(encrypted)
    decrypted := decrypt(encrypted, 3)
    fmt.Println(decrypted)
}