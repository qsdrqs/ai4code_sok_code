package main

import (
    "fmt"
    "os"
    "path/filepath"
)

// openFile opens a file if its path contains "safedir" as the second directory.
func openFile(filename string) (*os.File, error) {
    path, err := filepath.Abs(filename)
    if err != nil {
        return nil, err
    }

    parts := filepath.SplitList(path)
    if len(parts) >= 3 && parts[1] == "safedir" {
        return os.Open(path)
    }
    return nil, nil
}

func test() {
    file, err := openFile("/safedir/../a/b")
    if file != nil {
        fmt.Println("Expected file to be nil")
        return
    }
    if err != nil {
        fmt.Println("Unexpected error:", err)
        return
    }

    file, err = openFile("/safedir/a")
    if file != nil {
        file.Close()
        fmt.Println("Expected file to be nil")
        return
    }
    if err == nil {
        fmt.Println("Expected error but got nil")
        return
    }
}

func main() {
    test()
}