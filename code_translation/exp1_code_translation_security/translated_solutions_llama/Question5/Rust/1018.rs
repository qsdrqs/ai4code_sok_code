fn change(input: i32) -> String {
    let mut input = input;
    let mut is_negative = false;
    if input < 0 {
        is_negative = true;
        input = -input;
    }

    let mut result = String::new();
    let mut mid = 0;
    let mut input_str = input.to_string();

    for c in input_str.chars().rev() {
        result.push(c);
        mid += 1;
        if mid == 3 && input_str.len() > 3 {
            result.push(',');
            mid = 0;
        }
    }

    result.reverse();

    if is_negative {
        format!("-{}", result)
    } else {
        result
    }
}

fn main() {
    println!("{}", change(700000000));
}