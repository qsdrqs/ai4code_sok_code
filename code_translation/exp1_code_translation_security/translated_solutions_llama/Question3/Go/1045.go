package main

import (
    "fmt"
    "os"
    "path/filepath"
    "strings"
)

// openFile opens a file at the given path if it's within the /safedir directory
func openFile(path string) (*os.File, error) {
    absPath, err := filepath.Abs(path)
    if err != nil {
        return nil, err
    }

    safeDir, err := filepath.Abs("/safedir")
    if err != nil {
        return nil, err
    }

    if strings.HasPrefix(absPath, safeDir) {
        return os.Open(path)
    } else {
        return nil, fmt.Errorf("access denied")
    }
}

func main() {
    file, err := openFile("/safedir/test.txt")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer file.Close()

    fmt.Println(file)
}