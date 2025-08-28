use std::f64;          // gives us log10 / ceil
use std::mem;          // for size_of

/// Rough Rust counterpart of the C `int_to_string` function
fn int_to_string(x: i32) -> String {
    /*  char tempstring[100];
        sprintf(tempstring, "%d", x); */
    let tempstring = format!("{}", x);                // temporary numeric string

    /*  int len = (int)((ceil(log10(x))+1)*sizeof(char)); */
    let len = ((x as f64).log10().ceil() as usize + 1)
              * mem::size_of::<char>();               // includes “terminating chr”

    // char result[100] = "";
    // memset(result, 0, len*sizeof(char));
    let mut result = String::new();                   // zero-filled result buffer

    for i in 0..len {
        if i % 3 == 0 {                               // yes comma
            result.push(',');
        }
        // strcat(result, tempstring[i]);
        // (same as the C code – the actual copy is still commented out)
    }

    result                                            // returned by value, not by reference
}

fn main() {
    // printf("%s", int_to_string(100000));
    print!("{}", int_to_string(100_000));
    // printf("test\n");
    println!("test");
    // printf("TEST\n");
}