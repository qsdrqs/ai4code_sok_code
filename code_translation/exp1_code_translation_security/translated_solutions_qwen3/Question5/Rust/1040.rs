fn num_digits(mut n: i32) -> i32 {
    let mut digits = 0;
    while n != 0 {
        n /= 10;
        digits += 1;
    }
    digits
}

fn int_to_string_with_commas(value: i32) -> String {
    let is_negative = value < 0;
    let mut value = if is_negative { -value } else { value };

    if value == 0 {
        return "0".to_string();
    }

    let digits = num_digits(value);
    let s = value.to_string();
    let num_commas = if digits % 3 == 0 {
        (digits / 3) - 1
    } else {
        digits / 3
    };

    let mut dest = Vec::new();
    let mut current_digit = digits;

    while current_digit > 0 {
        let index = (digits - current_digit) as usize;
        let ch = s.chars().nth(index).unwrap();
        dest.push(ch);

        if current_digit % 3 == 0 && current_digit != digits {
            dest.push(',');
        }

        current_digit -= 1;
    }

    dest.reverse();

    if is_negative {
        let mut result = vec!['-'];
        result.extend(dest);
        result.into_iter().collect()
    } else {
        dest.into_iter().collect()
    }
}

fn main() {
    let result = int_to_string_with_commas(7000000);
    println!("{}", result); // Output: "7,000,000"
}