use std::fmt::Write;

fn num_to_str(num: i32) -> String {
    let mut str = num.to_string();
    
    let length = str.len();
    
    if num == 0 {
        return str;
    } else if num > 0 {
        let num_commas = (length - 1) / 3;
        if num_commas == 0 {
            return str;
        }
        
        let mut chars: Vec<char> = str.chars().collect();
        let mut new_chars = vec![' '; length + num_commas];
        
        let mut digits_moved = 0;
        let mut src_idx = chars.len() as i32 - 1;
        let mut dst_idx = new_chars.len() as i32 - 1;
        
        while src_idx >= 0 {
            if digits_moved < 3 {
                new_chars[dst_idx as usize] = chars[src_idx as usize];
                digits_moved += 1;
                src_idx -= 1;
            } else {
                new_chars[dst_idx as usize] = ',';
                digits_moved = 0;
            }
            dst_idx -= 1;
        }
        
        str = new_chars.into_iter().collect();
    } else {
        let num_commas = (length - 2) / 3;
        if num_commas == 0 {
            return str;
        }
        
        let mut chars: Vec<char> = str.chars().collect();
        let mut new_chars = vec![' '; length + num_commas];
        
        // Copy the negative sign
        new_chars[0] = chars[0];
        
        let mut digits_moved = 0;
        let mut src_idx = chars.len() as i32 - 1;
        let mut dst_idx = new_chars.len() as i32 - 1;
        
        while src_idx > 0 {
            if digits_moved < 3 {
                new_chars[dst_idx as usize] = chars[src_idx as usize];
                digits_moved += 1;
                src_idx -= 1;
            } else {
                new_chars[dst_idx as usize] = ',';
                digits_moved = 0;
            }
            dst_idx -= 1;
        }
        
        str = new_chars.into_iter().collect();
    }
    
    str
}

fn main() {
    let num = 7042;
    let str = num_to_str(num);
    
    println!("Output: {}", str);
}