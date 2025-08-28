package main

import (
    "os"
    "strings"
)

func readIn(path string) *os.File {
    if strings.Contains(path, "/safedir") {
        file, err := os.Open(path)
        if err != nil {
            // Handle error appropriately - could return nil or panic
            // For this translation, returning nil to match Python's behavior
            return nil
        }
        return file
    } else {
        return nil
    }
}