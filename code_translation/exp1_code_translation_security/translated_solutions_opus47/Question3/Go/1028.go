package main

import (
	"fmt"
	"io"
	"os"
)

func openFile(filePath string) *os.File {
	file, err := os.Open(filePath)
	if err != nil {
		panic(err)
	}
	return file
}

func main() {
	filePath := "/home/codex/Documents/User_Study_UI/prac.txt"

	f, err := os.Create(filePath)
	if err != nil {
		panic(err)
	}
	_, err = f.WriteString("Hello it's Melo")
	if err != nil {
		panic(err)
	}
	f.Close()

	file := openFile(filePath)
	defer file.Close()

	content, err := io.ReadAll(file)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(content))
}