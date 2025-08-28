fn num_digits(n: i32) -> u32 {
    if n == 0 {
        return 1;
    }
    let mut count = 0;
    let mut num = n.abs();
    while num > 0 {
        num /= 10;
        count += 1;
    }
    count
}

fn return_string(num: i32) -> String {
    // Convert the number to a string
    let num_str = num.to_string();
    let reversed: String = num_str.chars().rev().collect();
    let mut result = String::new();

    // Insert commas every 3 digits from the right
    for (i, c) in reversed.char_indices() {
        result.push(c);
        if (i + 1) % 3 == 0 && (i + 1) < reversed.len() {
            result.push(',');
        }
    }

    // Reverse the result to get the correct order
    let mut final_str: String = result.chars().rev().collect();

    // Handle negative numbers
    if num < 0 {
        final_str.insert(0, '-');
    }

    final_str
}

fn main() {
    let num = 7000000;
    let s = return_string(num);
    println!("{}", s);
}