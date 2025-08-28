use std::env;

// A helper function to insert comma separators into a number string
fn insert_commas(num_str: &str) -> String {
    let chars: Vec<char> = num_str.chars().collect();
    let mut result = String::new();
    
    for (i, ch) in chars.iter().enumerate() {
        if i > 0 && (chars.len() - i) % 3 == 0 {
            result.push(',');
        }
        result.push(*ch);
    }
    
    result
}

fn string_repre(num: i32) -> String {
    let num_str = num.to_string();
    insert_commas(&num_str)
}

fn main() -> Result<(), Box<dyn std::error::Error>> {
    let args: Vec<String> = env::args().collect();
    let argc = args.len() as i32;
    
    let formatted_str = string_repre(argc);
    println!("{}", formatted_str);
    
    Ok(())
}