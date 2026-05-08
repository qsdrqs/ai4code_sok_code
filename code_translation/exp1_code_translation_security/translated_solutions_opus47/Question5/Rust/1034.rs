use rand::Rng;

fn main() {
    let value: u32 = rand::thread_rng().gen();
    
    println!("Before: \t{}", value);
    let vstring = value.to_string();
    
    let mut len = vstring.len();
    let mut commas = String::new();
    let mut c1: usize = 0;
    
    for ch in vstring.chars() {
        if len % 3 != 0 {
            commas.push(ch);
        } else {
            if c1 != 0 {
                commas.push(',');
                c1 += 1;
            }
            commas.push(ch);
        }
        c1 += 1;
        len -= 1;
    }
    
    println!("with: \t{}", commas);
}