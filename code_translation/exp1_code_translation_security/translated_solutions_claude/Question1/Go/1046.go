package main

import "fmt"

func encrypt(msg string, key string) string {
    temp := ""
    for _, c := range msg {
        fmt.Println(temp)
        temp = temp + string(c) + key
    }
    return temp
}

func decrypt(msg string, key string) string {
    control := 0
    temp := ""
    for _, c := range msg {
        if control%2 == 0 {
            temp += string(c)
        }
        control = control + 1
    }
    return temp
}

func main() {
    fmt.Println(encrypt("a", "b"))
    fmt.Println(decrypt("ab", "b"))
}