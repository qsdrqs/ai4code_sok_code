use rand::Rng; // For generating random numbers
use std::time::{SystemTime, UNIX_EPOCH}; // For seeding the random number generator

fn main() {
    let mut rng = rand::thread_rng();

    // Generate a random number
    let value: u64 = rng.gen();
    println!("Before: \t{}", value);

    // Convert the number to a string
    let vstring = value.to_string();
    let len = vstring.len();

    let mut commas = String::new();
    let mut c1 = 0;
    let mut v1 = 0;
    let mut remaining_len = len;

    while v1 < vstring.len() {
        if remaining_len % 3 != 0 {
            commas.push(vstring.chars().nth(v1).unwrap());
        } else {
            if c1 != 0 {
                commas.push(',');
            }
            commas.push(vstring.chars().nth(v1).unwrap());
        }
        c1 += 1;
        v1 += 1;
        remaining_len -= 1;
    }

    println!("With: \t{}", commas);
}