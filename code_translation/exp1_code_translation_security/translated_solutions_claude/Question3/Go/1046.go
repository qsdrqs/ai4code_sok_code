package main

import (
    "os"
    "strings"
)

func openFile(path string) (*os.File, error) {
    parts := strings.SplitN(path, "/safedir", 2)
    var newpath string
    
    if len(parts) == 2 {
        // "/safedir" was found, reconstruct with separator and tail
        newpath = "/safedir" + parts[1]
    } else {
        // "/safedir" was not found, use empty string (equivalent to Python's behavior)
        newpath = ""
    }
    
    if newpath == "" {
        return nil, os.ErrNotExist
    }
    
    return os.Open(newpath)
}