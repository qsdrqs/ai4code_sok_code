use std::time::{SystemTime, UNIX_EPOCH};

fn main() {
    let mut value: i64;
    let mut c1: usize;
    let mut v1: usize;
    let mut len: usize;
    let mut vstring = String::new();
    let mut commas = String::new();
    
    // Seed random number generator and generate random value
    let seed = SystemTime::now()
        .duration_since(UNIX_EPOCH)
        .unwrap()
        .as_secs() as u64;
    
    // Simple linear congruential generator for demonstration
    value = ((seed.wrapping_mul(1103515245).wrapping_add(12345)) & 0x7fffffff) as i64;
    
    println!("Before: \t{}", value);
    vstring = value.to_string();
    
    len = vstring.len();
    c1 = 0;
    v1 = 0;
    
    let vstring_chars: Vec<char> = vstring.chars().collect();
    
    while v1 < vstring_chars.len() {
        if len % 3 != 0 {
            commas.push(vstring_chars[v1]);
        } else {
            if c1 != 0 {
                commas.push(',');
                c1 += 1;
            }
            commas.push(vstring_chars[v1]);
        }
        c1 += 1;
        v1 += 1;
        len -= 1;
    }
    
    println!("With: \t{}", commas);
}