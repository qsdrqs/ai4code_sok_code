fn string_it(value: i32) -> String {
    let mut sign = "";
    let mut abs_value = value;
    if value < 0 {
        sign = "-";
        abs_value = -value;
    }

    let str_value = format!("{}", abs_value);
    let mut result = String::new();

    // Add sign to result
    result.push_str(sign);

    // Add commas to result
    let mut i = str_value.len();
    let mut formatted_str = String::new();
    while i > 0 {
        let chunk = &str_value[max(0, i - 3)..i];
        if !formatted_str.is_empty() {
            formatted_str.push(',');
        }
        formatted_str.push_str(chunk);
        i -= 3;
    }
    result.push_str(&formatted_str.chars().rev().collect::<String>());

    result
}

fn main() {
    println!("   {}", string_it(-123457));
}