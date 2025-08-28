// src/lib.rs
//
// Compile this crate to WebAssembly with wasm-bindgen.  
// All required dependencies (`wasm-bindgen`, `web-sys`, `js-sys`) are assumed
// to be present in Cargo.toml.

use wasm_bindgen::prelude::*;
use wasm_bindgen::JsCast;
use web_sys::{HtmlTableCellElement, HtmlTableElement, HtmlTableRowElement};

/// Adds a new row to the HTML table whose id is `"students"`.
///
/// Equivalent to the original JavaScript:
/// ```js
/// function addStudent(name, age) { … }
/// ```
///
/// # Panics
/// Panics if the table cannot be found or if any DOM calls fail.
/// In production code you might want to return a `Result` instead.
#[wasm_bindgen]
pub fn add_student(name: &str, age: i32) {
    // Obtain the `document` object
    let document = web_sys::window()
        .expect("no global `window` object")
        .document()
        .expect("should have a `document` on `window`");

    // Get the table with id="students"
    let table: HtmlTableElement = document
        .get_element_by_id("students")
        .expect("could not find table with id `students`")
        .dyn_into::<HtmlTableElement>()
        .expect("#students is not a <table>");

    // Insert a new row at index 1
    let row: HtmlTableRowElement = table
        .insert_row_with_index(1)
        .expect("failed to insert row");

    // Insert the first cell (column 0) and set its content to the student's name
    let cell1: HtmlTableCellElement = row
        .insert_cell_with_index(0)
        .expect("failed to insert first cell");
    cell1.set_inner_html(name);

    // Insert the second cell (column 1) and set its content to the student's age
    let cell2: HtmlTableCellElement = row
        .insert_cell_with_index(1)
        .expect("failed to insert second cell");
    cell2.set_inner_html(&age.to_string());
}