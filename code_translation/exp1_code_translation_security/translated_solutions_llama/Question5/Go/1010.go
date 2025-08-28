package main

import (
    "fmt"
    "strconv"
    "strings"
)

// stringIt returns a string containing the expansion of the signed int
func stringIt(value int) string {
    // Convert the integer to a string
    str := strconv.Itoa(value)

    // If the value is negative, remove the negative sign and store it separately
    var sign string
    if value < 0 {
        sign = "-"
        str = str[1:]
    }

    // Add commas to the string
    str = strings.ReplaceAll(str, "", ",")
    str = strings.Join(strings.Split(str, ""), ",")

    // Reverse the string to correctly place commas
    strRunes := []rune(str)
    for i, j := 0, len(strRunes)-1; i < j; i, j = i+1, j-1 {
        strRunes[i], strRunes[j] = strRunes[j], strRunes[i]
    }
    str = string(strRunes)

    // Add commas every 3 digits
    var result string
    for i := len(str) - 1; i >= 0; i-- {
        result = str[i] + result
        if (len(str)-i)%3 == 0 && i != 0 {
            result = "," + result
        }
    }

    // Add the sign back to the string
    result = sign + result

    return result
}

func main() {
    fmt.Println("   ", stringIt(-123457))
}