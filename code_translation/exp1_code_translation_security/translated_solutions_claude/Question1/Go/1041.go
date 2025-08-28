package main

import "fmt"

func encrypt(str string, key int) string {
    newString := ""
    for _, char := range str {
        newString += string(rune(char) + rune(key))
    }
    return newString
}

func decrypt(str string, key int) string {
    newString := ""
    for _, char := range str {
        newString += string(rune(char) - rune(key))
    }
    return newString
}

func main() {
    msg := encrypt("this is cool if it really works", 2)
    res := decrypt(msg, 2)
    fmt.Println(msg)
    fmt.Println(res)
}