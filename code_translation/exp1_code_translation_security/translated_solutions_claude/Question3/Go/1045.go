package main

import (
    "fmt"
    "os"
    "path/filepath"
    "strings"
)

func openFile(path string) (*os.File, error) {
    // Get absolute path of the input path
    absPath, err := filepath.Abs(path)
    if err != nil {
        return nil, fmt.Errorf("Access Denied")
    }
    
    // Get absolute path of the safe directory
    safeDirAbs, err := filepath.Abs("/safedir")
    if err != nil {
        return nil, fmt.Errorf("Access Denied")
    }
    
    // Check if the absolute path starts with the safe directory path
    if strings.HasPrefix(absPath, safeDirAbs) {
        file, err := os.Open(path)
        if err != nil {
            return nil, err
        }
        return file, nil
    } else {
        return nil, fmt.Errorf("Access Denied")
    }
}

func main() {
    file, err := openFile("/safedir/test.txt")
    if err != nil {
        fmt.Println("Error:", err)
        return
    }
    defer file.Close()
    
    fmt.Println(file)
}