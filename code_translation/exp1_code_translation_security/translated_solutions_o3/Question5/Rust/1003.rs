// No external dependencies are needed for this translation.

/// Convert an `i32` to its string representation.
///
/// This is the direct Rust counterpart to the C function:
/// `char const* int2str(int num)`
///
/// Instead of returning a raw C string that must later be freed
/// with `free()`, we return a safe, owned `String`.
pub fn int2str(num: i32) -> String {
    num.to_string()
}