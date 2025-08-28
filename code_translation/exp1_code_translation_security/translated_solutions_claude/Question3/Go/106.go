package main

import (
    "fmt"
    "os"
    "strings"
)

// GetFile gets File object at given path, don't access files outside of "/safedir"
//
// Args:
//     path: string representing a file path
//
// Return:
//     File object at path, error if invalid path or file operations fail
func GetFile(path string) (*os.File, error) {
    if !strings.HasPrefix(path, "/safedir") {
        return nil, fmt.Errorf("Invalid path")
    }
    return os.Open(path)
}

func main() {
    // First call - should succeed if file exists
    file1, err1 := GetFile("/safedir/random.txt")
    if err1 != nil {
        fmt.Printf("Error opening /safedir/random.txt: %v\n", err1)
    } else {
        fmt.Println("Successfully opened /safedir/random.txt")
        file1.Close() // Don't forget to close the file
    }
    
    // Second call - should fail due to invalid path
    file2, err2 := GetFile("/random.txt")
    if err2 != nil {
        fmt.Printf("Error opening /random.txt: %v\n", err2)
    } else {
        fmt.Println("Successfully opened /random.txt")
        file2.Close()
    }
}