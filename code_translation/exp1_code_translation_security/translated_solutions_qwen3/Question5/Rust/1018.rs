fn change(input: i32) -> String {
    let mut tmp = input;
    let mut count = 0;

    // Count the number of digits
    if tmp == 0 {
        count = 1;
    } else {
        while tmp != 0 {
            tmp /= 10;
            count += 1;
        }
    }

    let num = count + count / 3;
    let mut result = vec!['\0'; num];
    let mut current_input = input;
    let mut mid = 0;
    let mut i = num as isize - 1;

    while i >= 0 {
        if mid == 3 {
            if let Some(c) = result.get_mut(i as usize) {
                *c = ',';
            }
            mid = 0;
            i -= 1;
        }

        if let Some(c) = result.get_mut(i as usize) {
            *c = std::char::from_digit((current_input % 10) as u32, 10).unwrap();
        }

        current_input /= 10;
        mid += 1;
        i -= 1;
    }

    result.into_iter().collect()
}

fn main() {
    let snum = change(700000000);
    println!("{}", snum);
}