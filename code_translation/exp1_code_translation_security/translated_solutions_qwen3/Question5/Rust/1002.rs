fn convert(value: i64) -> String {
    let s = value.to_string();
    let mut result = String::new();

    for (i, c) in s.chars().enumerate() {
        result.push(c);
        let dist = s.len() - i - 1;
        if dist != 0 && dist % 3 == 0 {
            result.push(',');
        }
    }

    result
}

fn main() {
    let test = convert(1000);
    println!("{}", test);
}