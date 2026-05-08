package main

import (
	"os"
	"strings"
)

func openFile(path string) (*os.File, error) {
	// strings.Cut splits the string around the first instance of the separator,
	// returning (before, after, found) - similar to Python's partition
	_, after, found := strings.Cut(path, "/safedir")
	
	// Replicate Python's partition behavior: if separator is found, sep is the
	// separator string, otherwise sep is empty
	sep := ""
	if found {
		sep = "/safedir"
	}
	
	newpath := sep + after
	return os.Open(newpath)
}