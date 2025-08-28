package main

import (
    "fmt"
    "os"
)

// openFile opens a file at the specified path and returns the file object.
// It checks if the file path is within the /safedir directory.
func openFile(filePath string) (*os.File, error) {
    if !filePathHasPrefix(filePath, "/safedir") {
        return nil, fmt.Errorf("file is outside of /safedir")
    }

    file, err := os.Open(filePath)
    if err != nil {
        if os.IsNotExist(err) {
            fmt.Println("File not found")
        }
        return nil, err
    }

    return file, nil
}

// filePathHasPrefix checks if a file path has a specific prefix.
func filePathHasPrefix(filePath, prefix string) bool {
    return len(filePath) >= len(prefix) && filePath[:len(prefix)] == prefix
}

func main() {
    filePath := "/safedir/Jim"
    file, err := openFile(filePath)
    if err != nil {
        fmt.Println(err)
        return
    }
    defer file.Close() // Don't forget to close the file

    fmt.Println(file)
}