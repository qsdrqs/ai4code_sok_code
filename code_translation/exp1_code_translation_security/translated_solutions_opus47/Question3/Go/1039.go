import "path/filepath"

func returnFile(path string) *os.File {
	cleanPath := filepath.Clean(path)
	if !strings.HasPrefix(cleanPath, "/safedir/") && cleanPath != "/safedir" {
		fmt.Println("File cannot be accessed")
		return nil
	}
	file, err := os.Open(cleanPath)
	if err != nil {
		return nil
	}
	return file
}