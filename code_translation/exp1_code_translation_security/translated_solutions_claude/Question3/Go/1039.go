package main

import (
    "fmt"
    "os"
    "strings"
)

func returnFile(path string) *os.File {
    if !strings.HasPrefix(path, "/safedir") {
        fmt.Println("File cannot be accessed")
        return nil
    }
    
    file, err := os.Open(path)
    if err != nil {
        fmt.Printf("Error opening file: %v\n", err)
        return nil
    }
    
    return file
}