use std::env;

// A helper function to insert a comma into the string at the given index.
// Copies the original string from the beginning up to `index`, inserts a comma,
// then appends the rest.
fn insert(s: &str, index: usize) -> String {
    let mut result = String::with_capacity(s.len() + 1);
    result.push_str(&s[..index]);
    result.push(',');
    result.push_str(&s[index..]);
    result
}

// Converts a number to its string representation with commas as thousands separators.
fn string_repre(num: i32) -> String {
    let mut strnum = num.to_string();
    let l = strnum.len(); // length before insertion

    // Number of commas to insert (we subtract 1 so numbers with length
    // exactly divisible by 3 don't get a leading comma, e.g. "123" not ",123")
    let conum = if l > 0 { (l - 1) / 3 } else { 0 };

    // Insert commas from right to left so earlier indices stay valid
    for i in 1..=conum {
        // `l - 3*i` is the index (in the original-length reference frame)
        // where the next comma should be inserted
        strnum = insert(&strnum, l - 3 * i);
    }

    strnum
}

fn main() {
    let args: Vec<String> = env::args().collect();
    let argc = args.len() as i32;
    let result = string_repre(argc);
    println!("{}", result);
}