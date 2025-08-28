package main

import (
    "fmt"
    "os"
    "strings"
)

func funcPath(path string) interface{} {
    if strings.Contains(path, "/safedir") {
        file, err := os.Open(path)
        if err != nil {
            return err
        }
        return file
    } else {
        return "file not found or unsafe for access."
    }
}

func main() {
    result := funcPath("/safedir/Jim")
    fmt.Println(result)
}