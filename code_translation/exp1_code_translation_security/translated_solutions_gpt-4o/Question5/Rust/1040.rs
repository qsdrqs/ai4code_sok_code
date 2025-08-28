use std::cmp::Ordering;

fn num_digits(mut n: i32) -> usize {
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

    let digits = num_digits(value);
    let mut num_commas = digits / 3;
    if digits % 3 == 0 {
        num_commas -= 1;
    }

    let mut s = value.to_string();
    let mut dest = String::with_capacity(digits + num_commas);

    let mut index = s.len() as isize - 1;
    let mut count = 0;

    while index >= 0 {
        dest.insert(0, s.chars().nth(index as usize).unwrap());
        count += 1;
        if count % 3 == 0 && index > 0 {
        }
    }
}