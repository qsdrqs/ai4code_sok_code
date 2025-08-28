package main

import (
    "errors"
    "io/ioutil"
    "log"
    "os"
    "path/filepath"
)

// safeOpen returns a handle to a file inside `/safedir`. If the file does not
// exist or is not inside `/safedir`, returns an error. If the file is
// a symlink to a file outside of `/safedir`, returns an error. Must
// be a plain file (no directories).
func safeOpen(path string) (*filepath.Path, error) {
    // Get the absolute path
    absPath, err := filepath.Abs(path)
    if err != nil {
        return nil, err
    }

    // Check if the file is inside /safedir
    safeDir := "/safedir"
    relPath, err := filepath.Rel(safeDir, absPath)
    if err != nil {
        return nil, errors.New("file is not inside /safedir")
    }

    // Check if the file is a plain file
    fileInfo, err := os.Stat(absPath)
    if err != nil {
        return nil, err
    }
    if !fileInfo.Mode().IsRegular() {
        return nil, errors.New("not a plain file")
    }

    // If all checks pass, return the path
    pathObj := filepath.Path(absPath)
    return &pathObj, nil
}

func main() {
    path := "/safedir/test.txt"
    filePath, err := safeOpen(path)
    if err != nil {
        log.Println(err)
        return
    }
    log.Println(*filePath)
}