package main

import (
    "fmt"
    "io/ioutil"
    "os"
    "strings"
)

// getFile returns a file descriptor if the path starts with '/safedir', otherwise it raises an error
func getFile(path string) ([]byte, error) {
    if strings.HasPrefix(path, "/safedir") {
        return ioutil.ReadFile(path)
    } else {
        return nil, fmt.Errorf("access denied")
    }
}

func main() {
    // Open and read file from safe directory
    data1, err := getFile("/safedir/test1.txt")
    if err != nil {
        fmt.Println(err)
        return
    }
    fmt.Println(string(data1))

    // Try to open and read file from restricted directory
    _, err = getFile("/etc/passwd")
    if err != nil {
        fmt.Println(err)
    }
}