package main

import (
    "fmt"
    "strconv"
)

func main() {
    // define the integer you want to convert
    var number int
    number = -12
    
    // define the string you want to store the integer in
    var str string
    
    // convert the integer to a string
    str = strconv.Itoa(number)
    
    // print the string
    fmt.Printf("%s\n", str)
}