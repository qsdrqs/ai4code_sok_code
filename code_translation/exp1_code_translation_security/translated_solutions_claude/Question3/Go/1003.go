package main

import (
    "os"
    "path/filepath"
    "strings"
)

// safeOpen returns a path to a file inside "/safedir". If the file does not
// exist or is not inside "/safedir", returns an empty string. If the file is
// a symlink to a file outside of "/safedir", returns an empty string. Must
// be a plain file (no directories).
//
// Returns a string path which you can then use with os.Open, os.ReadFile,
// or other file operations.
func safeOpen(path string) string {
    // Resolve the path (equivalent to pathlib.Path(path).resolve())
    absPath, err := filepath.Abs(path)
    if err != nil {
        return ""
    }
    
    // Evaluate symlinks to get the real path
    realPath, err := filepath.EvalSymlinks(absPath)
    if err != nil {
        return ""
    }
    
    // Clean the path to normalize it
    realPath = filepath.Clean(realPath)
    
    // Check if the path is inside /safedir (equivalent to path.relative_to("/safedir"))
    safedir := "/safedir"
    if !strings.HasPrefix(realPath, safedir) {
        return ""
    }
    
    // Ensure it's actually within safedir and not just a path that starts with it
    // For example, "/safedir2/file" should not be allowed
    if len(realPath) > len(safedir) && realPath[len(safedir)] != filepath.Separator {
        return ""
    }
    
    // Check if it's a regular file (equivalent to path.is_file())
    info, err := os.Stat(realPath)
    if err != nil {
        return ""
    }
    
    if !info.Mode().IsRegular() {
        return ""
    }
    
    return realPath
}