package main

import (
    "fmt"
    "io"
    "os"
    "path/filepath"
)

func getFile(path string) (*os.File, error) {
    // Normalize the path
    normPath := filepath.Clean(path)
    fmt.Println(normPath)

    // Check if the path is within the safe directory
    if !filepath.HasPrefix(normPath, "/safedir") {
        fmt.Println("out of bounds")
        // Return a file descriptor for /dev/null
        return os.Open("/dev/null")
    } else {
        fmt.Println("opening file", normPath)
        // Open the file in read and write mode
        return os.OpenFile(normPath, os.O_RDWR, 0666)
    }
}

func main() {
    // Get a file descriptor for /safedir/test1.txt
    f, err := getFile("/safedir/test1.txt")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer f.Close()

    // Read and print the contents of the file
    buf := make([]byte, 1024)
    n, err := f.Read(buf)
    if err != nil && err != io.EOF {
        fmt.Println(err)
        return
    }
    fmt.Println(string(buf[:n]))

    // Get a file descriptor for /dev/rand0
    g, err := getFile("/dev/rand0")
    if err != nil {
        fmt.Println(err)
        return
    }
    defer g.Close()

    // Read and print the contents of the file
    buf = make([]byte, 1024)
    n, err = g.Read(buf)
    if err != nil && err != io.EOF {
        fmt.Println(err)
        return
    }
    fmt.Println(string(buf[:n]))
}