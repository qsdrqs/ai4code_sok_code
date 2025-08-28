/*
Original C Code:

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

char* strrev(char* input)
 {
    char *start = input;
    char *end = input + strlen(input) - 1;
    char temp;
    while (end > start)
    {
        temp = *start;
        *start = *end;
        *end = temp;
        start++;
        end--;
    }
    return input;
}
char* integerToString(int num) {
  char* result = malloc(sizeof(char) * 100);
  int i = 0;
  int isNegative = 0;

  if (num < 0) {
    isNegative = 1;
    num = -num;
  }

  do {
    int digit = num % 10;
    result[i++] = '0' + digit;
    num /= 10;
  } while (num != 0);

  if (isNegative) {
    result[i++] = '-';
  }

  result[i] = '\0';

  return strrev(result);
}

int main(void) {
  char* num = integerToString(12345);
  printf("%s\n", num);
  free(num);

  num = integerToString(-12345);
  printf("%s\n", num);
  free(num);

  return 0;
}
*/

// Translated Rust Code:

/// A function that reverses a string.
///
/// Note: The original C function `strrev` reverses a C-style string in place.
/// This Rust version takes a string slice and returns a new, reversed `String`.
/// This is more idiomatic in Rust and correctly handles multi-byte UTF-8 characters.
fn strrev(input: &str) -> String {
    input.chars().rev().collect()
}

/// A function that takes a signed integer `num` and returns a string
/// representation of the integer.
fn integer_to_string(num: i32) -> String {
    // In idiomatic Rust, you would simply use the built-in `to_string` method:
    // return num.to_string();
    // This implementation translates the C algorithm for demonstration purposes.

    let mut result = String::new();
    let is_negative = num < 0;
    let mut n = num;

    // This loop emulates the C code's `do-while` loop. It correctly handles
    // the case where `num` is 0.
    loop {
        // Get the last digit. Using `.abs()` on the result of the modulo
        // operator works for both positive and negative numbers. This approach
        // also correctly handles i32::MIN without overflow, unlike the C
        // version which has undefined behavior for INT_MIN.
        let digit = (n % 10).abs();

        // Convert the integer digit to a character and push it to the string.
        // The string is built in reverse, just like in the C version.
        result.push((b'0' + digit as u8) as char);
        
        n /= 10;

        // The break condition for the `do-while` emulation.
        if n == 0 {
            break;
        }
    }

    if is_negative {
        result.push('-');
    }

    // The C code builds the string in reverse and then calls `strrev` to fix it.
    // We follow the same pattern here by calling our `strrev` helper function.
    strrev(&result)
}

fn main() {
    let num_str_1 = integer_to_string(12345);
    println!("{}", num_str_1);
    // In Rust, memory is managed automatically. The `String` returned by
    // `integer_to_string` is owned by `num_str_1`, and its memory is freed
    // when `num_str_1` goes out of scope. There is no need to call `free()`.

    let num_str_2 = integer_to_string(-12345);
    println!("{}", num_str_2);

    // Example with zero
    let num_str_3 = integer_to_string(0);
    println!("{}", num_str_3);
}