// File represents the input file object (dependency)
type File struct {
    Name string
    Path string
}

// getFilePath returns the file's path
func getFilePath(file File) string {
    return file.Name
}