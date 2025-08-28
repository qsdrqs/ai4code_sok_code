use rand::Rng;

fn main() {
    let value = rand::thread_rng().gen::<i32>() as i64;
    println!("Before: \t{}", value);
    
    let vstring = value.to_string();
    let len = vstring.len();
    let mut remaining = len;
    let mut v1 = 0;
    let mut c1 = 0;
    let mut commas = String::new();
    
    while v1 < vstring.len() {
        let c = vstring.chars().nth(v1).unwrap();
        
        if remaining % 3 != 0 {
            commas.push(c);
            c1 += 1;
        } else {
            if c1 != 0 {
                commas.push(',');
                c1 += 1;
            }
            commas.push(c);
            c1 += 1;
        }
        
        v1 += 1;
        remaining -= 1;
    }
    
    println!("with: \t{}", commas);
}