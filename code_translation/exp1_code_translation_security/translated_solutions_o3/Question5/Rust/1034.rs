// Transliteration of the supplied C program.
// ──────────────────────────────────────────
// External crates (such as `rand`) will be supplied for you; we therefore just
// `use` them here without worrying about the corresponding `Cargo.toml`.

use rand::Rng;

fn main() {
    /* ------------------------------------------------------------
     *                1.  Produce a random number
     * ---------------------------------------------------------- */
    //
    // `srand(time(NULL))` + `rand()` in C maps most closely to producing one
    // positive `u32` value with the thread-local RNG in Rust.
    //
    // (We stick to `u32`—C’s `rand()` is guaranteed to be non-negative.)
    let mut rng   = rand::thread_rng();
    let value: u32 = rng.gen();               // same spirit as C’s `rand()`

    println!("Before:\t{}", value);

    /* ------------------------------------------------------------
     *                2.  Turn it into a string
     * ---------------------------------------------------------- */
    //
    // Equivalent to:
    //     sprintf(vstring, "%ld", value);
    let vstring = value.to_string();
    let mut remaining_len = vstring.len() as i32;   // the running `len` in C

    /* ------------------------------------------------------------
     *                3.  Insert thousands separators
     * ---------------------------------------------------------- */
    let bytes = vstring.as_bytes();                 // ASCII, safe to treat as bytes

    let mut v1: usize = 0;                          // index in `vstring`
    let mut c1: usize = 0;                          // index in `commas`

    // 10 digits  -> at most 3 commas -> 13 + '\0'  ≈ 14 bytes.
    let mut commas: Vec<u8> = Vec::with_capacity(14);

    // Transliterated `while (vstring[v1]) { ... }`
    while v1 < bytes.len() {
        if remaining_len % 3 != 0 {
            // `commas[c1] = vstring[v1];`
            commas.push(bytes[v1]);
        } else {
            // Time to insert a comma—except before the very first group.
            if c1 != 0 {
                commas.push(b',');
                c1 += 1;
            }
            commas.push(bytes[v1]);
        }

        // Book-keeping identical to the C original
        c1 += 1;
        v1 += 1;
        remaining_len -= 1;
    }

    let final_string = String::from_utf8(commas).expect("Only digits & commas present");
    println!("With:\t{}", final_string);
}