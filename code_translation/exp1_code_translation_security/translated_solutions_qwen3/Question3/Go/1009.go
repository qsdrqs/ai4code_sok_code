func getFile(path string) (*os.File, error) {
	// Step 1: Resolve the absolute and symlink-free path of the SAFE directory
	safeBase, err := filepath.Abs(SAFE)
	if err != nil {
		return nil, err
	}
	safeEval, err := filepath.EvalSymlinks(safeBase)
	if err != nil {
		return nil, err
	}

	// Step 2: Join the SAFE directory with the requested path component-wise
	testPathComponents := filepath.Join(SAFE, path)
	testPath, err := filepath.Abs(testPathComponents)
	if err != nil {
		return nil, err
	}
	testEval, err := filepath.EvalSymlinks(testPath)
	if err != nil {
		return nil, err
	}

	// Step 3: Check if the resolved path is under the resolved SAFE directory
	if !strings.HasPrefix(testEval, safeEval) {
		return nil, fmt.Errorf("Filename %s is not in %s directory", testEval, SAFE)
	}

	// Ensure it's not a partial match (e.g., /safe2 is not under /safe)
	if testEval != safeEval && !strings.HasPrefix(testEval, safeEval+string(filepath.Separator)) {
		return nil, fmt.Errorf("Filename %s is not in %s directory", testEval, SAFE)
	}

	// Step 4: Open the file using the original string concatenation (SAFE + path)
	actualPath := SAFE + path
	file, err := os.Open(actualPath)
	if err != nil {
		return nil, err
	}

	return file, nil
}