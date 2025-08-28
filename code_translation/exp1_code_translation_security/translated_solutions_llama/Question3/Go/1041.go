package main

import (
    "errors"
    "fmt"
    "os"
    "strings"
)

// openFile opens a file at the given path if it starts with /safedir
func openFile(path string) (*os.File, error) {
    if strings.HasPrefix(path, "/safedir") {
        // Go's os.Open function returns a *os.File and an error
        return os.Open(path)
    } else {
        return nil, errors.New("path must start with /safedir")
    }
}

func main() {
    // Open a file with a valid path
    res1, err := openFile("/safedir/random.txt")
    if err != nil {
        fmt.Println(err)
    } else {
        defer res1.Close() // Close the file when we're done
        fmt.Println(res1)
    }

    // Open a file with an invalid path
    _, err = openFile("blah")
    if err != nil {
        fmt.Println(err)
    }
}