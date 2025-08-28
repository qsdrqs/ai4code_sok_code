package main

type File struct {
    Name string
    Path string
}

func GetFilePath(file File) string {
    return file.Name
}