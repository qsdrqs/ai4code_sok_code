fn main() {
    let num = 1000000;
    let str = add_commas(num);
    println!("{}", str);
}

fn add_commas(num: u64) -> String {
    if num == 0 {
        return "0".to_string();
    }

    // Step 1: Convert the number to a vector of digits in reverse order
    let mut reversed_digits = Vec::new();
    let mut n = num;
    while n > 0 {
        let digit = (n % 10) as u8 as char;
        reversed_digits.push(digit);
        n /= 10;
    }

    // Step 2: Insert commas every 3 digits in the reversed list
    let mut with_commas = Vec::new();
    for (i, &d) in reversed_digits.iter().enumerate() {
        with_commas.push(d);
        if (i + 1) % 3 == 0 && (i + 1) < reversed_digits.len() {
            with_commas.push(',');
        }
    }

    // Step 3: Reverse the entire list (including commas) to get the final result
    with_commas.reverse();

    // Step 4: Convert the vector of chars into a String
    with_commas.into_iter().collect()
}