import "path/filepath"

func funcPath(path string) interface{} {
	cleaned := filepath.Clean(path)
	safeDir := "/safedir"
	if !strings.HasPrefix(cleaned, safeDir+string(os.PathSeparator)) && cleaned != safeDir {
		return "file not found or unsafe for access."
	}
	file, err := os.Open(cleaned)
	if err != nil {
		return err
	}
	return file
}