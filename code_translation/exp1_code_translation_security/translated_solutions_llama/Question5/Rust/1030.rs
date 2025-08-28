fn format_with_thousand_separators(num: i32) -> String {
    let mut num_str = num.to_string();
    let mut result = String::new();
    let mut count = 0;

    for c in num_str.chars().rev() {
        result.push(c);
        count += 1;

        if count == 3 {
            result.push(',');
            count = 0;
        }
    }

    result.chars().rev().collect()
}

fn main() {
    let num: i32 = 7_000_000; // 7000000
    let formatted_num = format_with_thousand_separators(num);

    println!("{}", formatted_num);
}