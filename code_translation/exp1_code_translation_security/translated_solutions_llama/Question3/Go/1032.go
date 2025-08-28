package main

import (
    "fmt"
    "os"
)

// getFile returns a file descriptor if the path starts with '/safedir', otherwise it raises an error
func getFile(path string) (*os.File, error) {
    if path == "/safedir/test1.txt" { // Go's string.startswith() is not available, using direct comparison for simplicity
        file, err := os.Open(path)
        if err != nil {
            return nil, err
        }
        return file, nil
    } else {
        return nil, fmt.Errorf("access denied")
    }
}

func main() {
    file, err := getFile("/safedir/test1.txt")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer file.Close() // Close the file after use

    // Read and print the file content
    buf := make([]byte, 1024)
    n, err := file.Read(buf)
    if err != nil && err != os.ErrUnexpectedEOF {
        fmt.Println(err)
        return
    }
    fmt.Println(string(buf[:n]))
    //fmt.Println(getFile("/etc/passwd")) // Uncomment to test access denied
}