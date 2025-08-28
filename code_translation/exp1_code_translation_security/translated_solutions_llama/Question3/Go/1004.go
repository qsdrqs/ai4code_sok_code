package main

import (
    "fmt"
    "os"
)

// getFile opens a file and returns a file object if the file path starts with "/safedir"
// If file is outside of directory "/safedir" or not found, it prints an error message and returns nil
func getFile(filePath string) *os.File {
    if !filePathHasPrefix(filePath, "/safedir") {
        fmt.Println("File must be inside /safedir")
        return nil
    }
    file, err := os.Open(filePath)
    if err != nil {
        fmt.Println("File not found")
        return nil
    }
    return file
}

// filePathHasPrefix checks if a file path has a given prefix
func filePathHasPrefix(filePath, prefix string) bool {
    return len(filePath) >= len(prefix) && filePath[:len(prefix)] == prefix
}

func main() {
    // Create a test file
    err := os.WriteFile("/safedir/test.txt", []byte("Hello"), 0644)
    if err != nil {
        fmt.Println(err)
        return
    }

    // Test getFile with a valid path
    testF := getFile("/safedir/test.txt")
    if testF != nil {
        buf := make([]byte, 1024)
        n, err := testF.Read(buf)
        if err != nil {
            fmt.Println(err)
            return
        }
        fmt.Println(string(buf[:n]))
        testF.Close()
    }

    // Test getFile with an invalid path
    testF = getFile("/test.txt") // Should fail
    if testF == nil {
        fmt.Println("File not opened")
    }
}