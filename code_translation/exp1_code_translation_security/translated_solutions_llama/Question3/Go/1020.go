package main

import (
    "fmt"
    "os"
)

// funcPath checks if the provided path contains "/safedir" and attempts to open the file.
// If the path is safe, it returns a file descriptor. Otherwise, it returns an error message.
func funcPath(path string) (interface{}, error) {
    if !hasSafeDir(path) {
        return "file not found or unsafe for access.", nil
    }

    file, err := os.Open(path)
    if err != nil {
        return nil, err
    }
    return file, nil
}

// hasSafeDir checks if the provided path contains "/safedir".
func hasSafeDir(path string) bool {
    return path != "" && len(path) >= 9 && path[0:9] == "/safedir"
}

func main() {
    path := "/safedir/Jim"
    result, err := funcPath(path)
    if err != nil {
        fmt.Println(err)
    } else if file, ok := result.(*os.File); ok {
        // If you want to use the file descriptor, you can do so here.
        // For simplicity, we just close it and print a success message.
        file.Close()
        fmt.Printf("File %s opened successfully.\n", path)
    } else {
        fmt.Println(result)
    }
}