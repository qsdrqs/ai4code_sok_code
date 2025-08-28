package main

import (
    "fmt"
    "os"
)

// getFile returns a file descriptor if the path starts with '/safedir', otherwise it returns an error
func getFile(path string) (*os.File, error) {
    if !path.startswith("/safedir") {
        return nil, fmt.Errorf("access denied")
    }
    return os.Open(path)
}

func main() {
    paths := []string{"/safedir/random.txt", "/etc/passwd"}
    for _, path := range paths {
        file, err := getFile(path)
        if err != nil {
            fmt.Printf("Error with %s: %v\n", path, err)
            continue
        }
        defer file.Close()
        buf, err := os.ReadFile(path)
        if err != nil {
            fmt.Printf("Error reading file %s: %v\n", path, err)
            continue
        }
        fmt.Printf("File %s contents:\n%s\n", path, string(buf))
    }
}