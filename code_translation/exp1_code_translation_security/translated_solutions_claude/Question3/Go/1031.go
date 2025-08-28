package main

import (
	"fmt"
	"os"
	"path/filepath"
)

func findPath(path string) *os.File {
	safePath := "/safedir"
	dirname := filepath.Dir(path)
	if dirname == safePath {
		file, err := os.Open(path)
		if err != nil {
			fmt.Printf("Error opening file: %v\n", err)
			return nil
		}
		fmt.Println("safe!")
		return file
	} else {
		fmt.Println("not safe!")
		return nil
	}
}

func main() {
	file1 := findPath("/safedir/random.txt")
	if file1 != nil {
		file1.Close()
	}
	
	file2 := findPath("/home/codex/Documents/User_Study_UI")
	if file2 != nil {
		file2.Close()
	}
}