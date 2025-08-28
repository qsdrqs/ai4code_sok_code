package main

import (
    "fmt"
    "os"
    "strings"
)

func openFile(filePath string) (*os.File, error) {
    if !strings.HasPrefix(filePath, "/safedir") {
        return nil, fmt.Errorf("File is outside of /safedir")
    }
    
    fileObj, err := os.Open(filePath)
    if err != nil {
        fmt.Println("File not found")
        return nil, nil
    }
    
    return fileObj, nil
}

func main() {
    path, err := openFile("/safedir/Jim")
    if err != nil {
        fmt.Println("Error:", err)
        return
    }
    fmt.Println(path)
}