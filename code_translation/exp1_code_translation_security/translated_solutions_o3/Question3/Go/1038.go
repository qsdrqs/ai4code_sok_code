package main

// File is a minimal stand-in for whatever concrete
// type you will actually receive in real code.
type File struct {
	Name string
	Path string
}

// GetFilePath mimics the Python version.
func GetFilePath(file File) string {
	return file.Name       // first return (executed)
	// return file.Path    // unreachable in Go, left for parity
}