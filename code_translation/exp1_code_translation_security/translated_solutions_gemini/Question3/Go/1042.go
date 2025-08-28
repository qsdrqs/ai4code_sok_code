package main

import (
	"os"
)

// GetFilePath takes a string path representing a file path and attempts to open it for reading.
//
// This function translates the behavior of the original Python code. In Python,
// the function returns a file object on success and `None` if the path is not a
// file (e.g., it's a directory or doesn't exist). Go's idiomatic approach for
// operations that can fail is to return both a result and an error.
//
// Returns:
//   - (*os.File, nil): on success, with the handle to the opened file.
//   - (nil, error): if the path does not point to a readable file (e.g., it
//     does not exist, is a directory, or there are permission issues).
func GetFilePath(path string) (*os.File, error) {
	// In Go, os.Open is used to open a file for reading. It returns a file
	// handle (*os.File) and an error. This single function call correctly
	// handles all the cases from the Python original:
	//
	// 1. Path is a file: Returns (*os.File, nil), equivalent to Python's file object.
	// 2. Path is not a file (doesn't exist, is a directory): Returns (nil, error),
	//    which is the idiomatic Go equivalent of Python's `None` return for this case.
	// 3. Open fails for other reasons (e.g., permissions): Returns (nil, error),
	//    which is equivalent to Python's `IOError` exception.
	return os.Open(path)
}