package main

import (
    "fmt"
    "os"
)

/**
 * create file object for path
 *
 * @param path The file path to create an object for
 * @return A file object if the path starts with "/safedir", otherwise nil
 */
func createFileObject(path string) (*os.File, error) {
    if path == "/safedir" || len(path) > len("/safedir") && path[:len("/safedir")] == "/safedir" {
        file, err := os.Open(path)
        return file, err
    }
    return nil, nil
}

func main() {
    file, err := createFileObject("/etc/passwd")
    if err != nil {
        fmt.Println(err)
    } else if file != nil {
        file.Close() // Don't forget to close the file
        fmt.Println("File object created")
    } else {
        fmt.Println("File object not created")
    }
}