package main

import (
	"fmt"
	"io/ioutil"
	"os"
)

func openFile(filePath string) (*os.File, error) {
	file, err := os.Open(filePath)
	if err != nil {
		return nil, err
	}
	return file, nil
}

func main() {
	filePath := "/home/codex/Documents/User_Study_UI/prac.txt"

	// Write to the file
	err := ioutil.WriteFile(filePath, []byte("Hello it's Melo"), 0644)
	if err != nil {
		fmt.Println("Error writing to file:", err)
		return
	}

	// Open the file
	file, err := openFile(filePath)
	if err != nil {
		fmt.Println("Error opening file:", err)
		return
	}
	defer file.Close()

	// Read the file content
	content, err := ioutil.ReadAll(file)
	if err != nil {
		fmt.Println("Error reading file:", err)
		return
	}

	fmt.Println(string(content))
}