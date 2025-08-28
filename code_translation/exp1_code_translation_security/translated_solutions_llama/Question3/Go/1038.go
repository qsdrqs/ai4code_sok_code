package main

import (
	"fmt"
	"os"
	"path/filepath"
)

// FileInfo holds information about a file
type FileInfo struct {
	Name string
	Path string
}

// GetFilePath returns the name and absolute path of a given file
func GetFilePath(file *os.File) (FileInfo, error) {
	absPath, err := filepath.Abs(file.Name())
	if err != nil {
		return FileInfo{}, err
	}
	return FileInfo{
		Name: file.Name(),
		Path: absPath,
	}, nil
}

func main() {
	// Example usage
	file, err := os.Open("example.txt")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()

	fileInfo, err := GetFilePath(file)
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Printf("File Name: %s, File Path: %s\n", fileInfo.Name, fileInfo.Path)
}