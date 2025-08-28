package main

import "fmt"

// intToString converts an integer to a string without commas.
func intToString(num int) string {
    temp := num
    length := 0

    // Calculate the number of digits
    for temp != 0 {
        temp /= 10
        length++
    }

    // Handle the special case of zero
    if length == 0 {
        return ""
    }

    // Build the string in reverse order
    str := make([]byte, length)
    numCopy := num
    for i := length - 1; i >= 0; i-- {
        str[i] = byte(numCopy%10) + '0'
        numCopy /= 10
    }

    return string(str)
}

// intToStringWithCommas formats the string with commas as thousand separators.
func intToStringWithCommas(num int) string {
    str := intToString(num)
    lenStr := len(str)

    // Calculate the number of commas needed
    commaCount := (lenStr - 1) / 3
    if (lenStr - 1) < 0 {
        commaCount += 1
    }
    if commaCount < 0 {
        commaCount = 0
    }

    // Create the new string with commas
    newStr := make([]byte, lenStr+commaCount)
    j := 0
    k := 0

    for i := 0; i < len(newStr); i++ {
        if (lenStr-j)%3 == 0 && k != 1 && i != 0 {
            newStr[i] = ','
            k = 1
        } else {
            newStr[i] = str[j]
            j++
            if k == 1 {
                k = 0
            }
        }
    }

    return string(newStr)
}

// main function to test the conversion
func main() {
    num := 7654321
    str := intToStringWithCommas(num)
    fmt.Println(str)
}