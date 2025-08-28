package main

import (
    "os"
)

func getFile(path string) *os.File {
    filename := "/safedir/" + path
    file, err := os.Open(filename)
    if err != nil {
        panic(err)
    }
    return file
}