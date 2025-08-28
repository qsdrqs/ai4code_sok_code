// main.rs

// External crates that will be supplied for you.
use locale_config::Locale;          // Reads the user’s current locale
use num_format::{Locale as NFLocale, ToFormattedString};

fn main() {
    // Same value as in the C example
    let num: i32 = 7_000_000;

    //----------------------------------------------------------------------
    // setlocale(LC_NUMERIC, "")               →  grab the process locale
    //----------------------------------------------------------------------
    let sys_locale = Locale::current().to_string();        // e.g. "en_US.UTF-8"
    let sys_locale = sys_locale.split('.').next().unwrap_or(""); // strip encoding

    //----------------------------------------------------------------------
    // Map the locale string we just obtained to one understood by the
    // `num-format` crate.  Only a handful of common locales are shown
    // here; add more if you need them.
    //----------------------------------------------------------------------
    let nf_locale = match sys_locale {
        "de_DE" | "de"         => NFLocale::de,
        "fr_FR" | "fr"         => NFLocale::fr,
        "it_IT" | "it"         => NFLocale::it,
        "ru_RU" | "ru"         => NFLocale::ru,
        "en_US" | "en_GB" |
        "en" | ""              => NFLocale::en, // fallback / default
        _                      => NFLocale::en, // anything else → English
    };

    //----------------------------------------------------------------------
    // sprintf(snum, "%'d", num)               →  locale-aware formatting
    //----------------------------------------------------------------------
    let formatted = num.to_formatted_string(&nf_locale);

    //----------------------------------------------------------------------
    // printf(snum);
    //----------------------------------------------------------------------
    print!("{}", formatted);
}