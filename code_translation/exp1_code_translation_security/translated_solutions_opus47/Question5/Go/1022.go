package main

import "fmt"

func printNum(num int32) {
	if num < 0 {
		fmt.Print("-")
		printNum(-num)
		return
	}
	if num < 1000 {
		fmt.Printf("%d", num)
		return
	}

	printNum(num / 1000)
	fmt.Printf(",%03d", num%1000)
}

func main() {
	printNum(-12345)
}