use num_format::{SystemLocale, ToFormattedString};

fn sig_num_to_str(num: i32) -> String {
    let locale = SystemLocale::default().unwrap();
    num.to_formatted_string(&locale)
}