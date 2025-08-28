use std::env;

fn int_to_string(x: i32) -> String {
    let temp_string = format!("{}", x);
    let len = temp_string.len();
    
    let mut result = String::new();
    
    for i in 0..len {
        if i % 3 == 0 {
            result.push(',');
        }
        // The original C code had this commented out:
        // result.push(temp_string.chars().nth(i).unwrap());
    }
    
    result
}

fn main() {
    let args: Vec<String> = env::args().collect();
    
    print!("{}", int_to_string(100000));
    println!("test");
}