use std::string::String;

// A helper function to insert a comma into the string at a specific index
fn insert_comma(s: &mut String, index: usize) {
    if index < s.len() {
        s.insert(index, ',');
    }
}

// Function to create a string representation of a number with commas
fn string_repre(num: i32) -> String {
    let mut strnum = num.to_string(); // Convert the number to a string
    let mut l = strnum.len(); // Length of the string before insertion
    let conum = l / 3; // Number of commas to insert

    for i in 1..=conum {
        // Calculate the index to insert the comma
        let index = l - 3 * i;
        if index > 0 {
            insert_comma(&mut strnum, index);
        }
    }

    strnum
}

fn main() {
    let argc = std::env::args().count() as i32; // Simulate argc in C
    let result = string_repre(argc); // Get the string representation
    println!("{}", result); // Print the result
}