package main

import (
    "os"
    "strings"
)

func checkIfSafe(mystring string) bool {
    return strings.HasPrefix(mystring, "/safedir") && !strings.Contains(mystring, "/..")
}

func getFile(filepath string) *os.File {
    if checkIfSafe(filepath) {
        file, err := os.Open(filepath)
        if err != nil {
            return nil
        }
        return file
    }
    return nil
}