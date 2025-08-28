package main

import (
	"fmt"
)

func int2str(num int) string {
	return fmt.Sprintf("%d", num)
}

func int2strWithCommas(num int) string {
	return fmt.Sprintf("%v,%v,%v,%v", 
		num/1000000, 
		(num/1000)%1000, 
		num%1000/100, 
		num%100)
}

func int2strWithCommasGeneral(num int) string {
    str := fmt.Sprintf("%d", num)
    var result string
    count := 0
    for i := len(str) - 1; i >= 0; i-- {
        if count == 3 {
            result = "," + result
            count = 0
        }
        result = string(str[i]) + result
        count++
    }
    return result
}

func main() {
	num := 1234567
	fmt.Println(int2strWithCommasGeneral(num))  // Output: 1,234,567
}