package main

import "os"

func getFilePath(file *os.File) string {
	return file.Name()
	// The second `return` in the Python code is unreachable, so it is omitted in the Go translation.
}