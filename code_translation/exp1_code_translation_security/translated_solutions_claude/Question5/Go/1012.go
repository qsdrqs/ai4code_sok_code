package main

import (
    "fmt"
    "strconv"
    "strings"
)

// a helper function to insert comma to the string
func insert(str string, index int) string {
    if index < 0 || index > len(str) {
        return str
    }
    return str[:index] + "," + str[index:]
}

func stringRepre(num int) string {
    strnum := strconv.Itoa(num)
    
    l := len(strnum) // before the insertion
    conum := l / 3
    l = l + conum // total length after insert
    buff := strnum // initialize buff with the original string
    
    for i := 1; i <= conum; i++ {
        // l - 3 + i - 1 is the index to be inserted
        buff = insert(buff, l-3+i-1)
    }
    
    return buff
}

func main() {
    argc := len(os.Args) // equivalent to argc in C
    str := stringRepre(argc)
    fmt.Println(str)
}