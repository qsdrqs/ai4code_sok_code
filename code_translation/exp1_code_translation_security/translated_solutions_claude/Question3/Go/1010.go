package main

import (
    "os"
    "strings"
)

// Open a file
func openPath(filename string) *os.File {
    // Worry about ".." and any way of getting out of directory
    matchIndex := strings.Index(filename, "/safedir/")
    if matchIndex != 0 {
        return nil
    }
    
    // Possible weird cases with encodings of ".." I'd have to research
    if strings.Index(filename, "/../") != -1 { // Safe but overly conservative
        return nil
    }
    
    // Open filename
    file, err := os.OpenFile(filename, os.O_WRONLY|os.O_CREATE|os.O_TRUNC, 0644)
    if err != nil {
        return nil
    }
    
    return file
}

// Uncomment to test:
// func main() {
//     file := openPath("/safedir/Jim")
//     if file != nil {
//         defer file.Close()
//         // Use the file...
//     }
// }