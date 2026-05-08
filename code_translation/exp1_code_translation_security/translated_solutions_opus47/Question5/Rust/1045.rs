fn power(base: i32, mut exponent: i32) -> i32 {
    let mut result = 1;
    while exponent > 0 {
        result *= base;
        exponent -= 1;
    }
    result
}

fn number_to_string(mut num: i32) -> String {
    let mut num_size: i32 = 0;
    let mut temp = num;
    if num < 0 {
        temp *= -1;
    }
    while temp > 0 {
        temp /= 10;
        num_size += 1;
    }
    let comma_count = (num_size - 1) / 3;
    let capacity = (num_size + comma_count + 1) as usize;
    let mut str_num = String::with_capacity(capacity);

    if num < 0 {
        str_num.push('-');
        num *= -1;
    }

    for j in (0..num_size).rev() {
        let digit = (num / power(10, j)) as u8 + b'0';
        str_num.push(digit as char);
        num %= power(10, j);
        if j % 3 == 0 && j != 0 {
            str_num.push(',');
        }
    }

    str_num
}

fn main() {
    let num = -7000000;
    let str_num = number_to_string(num);
    println!("{}", str_num);
}