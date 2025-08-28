package main

import (
    "fmt"
    "os"
    "path/filepath"
)

// openFile opens a file located in a specific directory.
func openFile(path string) (*os.File, error) {
    // Find the index of the "/safedir" substring
    idx := filepath.FromSlash(path).Find("/safedir")
    if idx == -1 {
        return nil, fmt.Errorf("'/safedir' not found in path")
    }

    // Construct the new path
    newPath := filepath.FromSlash(path)[idx+len("/safedir"):]

    // Open the file
    file, err := os.Open(newPath)
    if err != nil {
        return nil, err
    }

    return file, nil
}

func main() {
    // Example usage:
    path := "/path/to/safedir/example.txt"
    file, err := openFile(path)
    if err != nil {
        fmt.Println(err)
        return
    }
    defer file.Close()

    // Use the file
    fmt.Println("File opened successfully")
}