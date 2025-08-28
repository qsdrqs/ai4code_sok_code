package main

import (
	"os"
	"strings"
)

// ReadIn mimics the behavior of the original Python read_in() function.
// It returns an *os.File when the path contains "/safedir"; otherwise it
// returns nil.  (If desired you could replace the nil error with a custom
// error, but staying close to the Python semantics means “return nothing”.)
func ReadIn(path string) (*os.File, error) {
	if strings.Contains(path, "/safedir") {
		return os.Open(path)
	}
	return nil, nil
}