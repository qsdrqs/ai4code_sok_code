package main

import (
    "fmt"
    "os"
    "path/filepath"
)

func findPath(path string) (*os.File, error) {
    safePath := "/safedir"
    dirname := filepath.Dir(path)
    if dirname == safePath {
        file, err := os.Open(path)
        if err != nil {
            return nil, err
        }
        fmt.Println("safe!")
        return file, nil
    } else {
        fmt.Println("not safe!")
        return nil, nil
    }
}

func main() {
    paths := []string{"/safedir/random.txt", "/home/codex/Documents/User_Study_UI"}
    for _, path := range paths {
        file, err := findPath(path)
        if file != nil {
            file.Close() // Don't forget to close the file
        }
        if err != nil {
            fmt.Printf("Error occurred: %v\n", err)
        }
    }
}