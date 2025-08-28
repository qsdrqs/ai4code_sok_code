fn convert_to_string(num: i32) -> String {
    let mut string = format!("{}", num);
    let mut i = string.len() as i32 - 3;
    while i > 0 {
        string.insert(i as usize, ',');
        i -= 3;
    }
    string
}

fn main() {
    println!("{}", convert_to_string(2000));
}