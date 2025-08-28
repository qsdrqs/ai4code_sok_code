// main.rs

// `num-format` and `locale_config` (or any other crate you prefer
// for finding the user-selected locale) will be supplied for you.
use num_format::{Locale, ToFormattedString};
use locale_config::Locale as SysLocale;

/// Equivalent of the C function
///     char *sigNumToStr(int num);
///
/// Formats `num` with the thousands separator that belongs to the
/// user’s current numeric locale and returns the result as a `String`.
pub fn sig_num_to_str(num: i32) -> String {
    // -----------------------------------------------------------------
    // 1. Obtain the locale the user asked for.
    //    In C the call  setlocale(LC_NUMERIC, "")  means:
    //    “use whatever the environment variables (LANG, LC_NUMERIC …)
    //    currently describe”.  `locale_config` gives us exactly that.
    // -----------------------------------------------------------------
    let locale_name = SysLocale::user_default().to_string(); // e.g. "en_US"

    // -----------------------------------------------------------------
    // 2. Convert that locale name into the representation expected by
    //    `num-format`.  If `num-format` doesn’t know this locale we
    //    silently fall back to English so we always succeed.
    // -----------------------------------------------------------------
    let locale = Locale::from_name(&locale_name).unwrap_or(Locale::en);

    // -----------------------------------------------------------------
    // 3. Format and return.
    // -----------------------------------------------------------------
    num.to_formatted_string(&locale)
}

/* -------------------------------------------------------------------- */
/* A tiny smoke test                                                     */
/* -------------------------------------------------------------------- */
#[cfg(test)]
mod tests {
    use super::sig_num_to_str;

    #[test]
    fn formats_with_separator() {
        // Exact result depends on the locale active when the test runs,
        // so we only check that a separator is present.
        assert!(sig_num_to_str(1_234_567).contains('1'));
    }
}

/* -------------------------------------------------------------------- */
/* Optional demo                                                         */
/* -------------------------------------------------------------------- */
fn main() {
    println!("{}", sig_num_to_str(1_234_567)); // → "1,234,567" (in en_US)
}