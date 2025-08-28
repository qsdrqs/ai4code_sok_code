use wasm_bindgen::prelude::*;
use web_sys::{HtmlTableElement, HtmlTableRowElement, HtmlTableCellElement};

// Define a function to add a student to the table
#[wasm_bindgen]
pub fn add_student(name: String, age: i32) {
    // Get the table element
    let window = web_sys::window().unwrap();
    let document = window.document().unwrap();
    let table: HtmlTableElement = document.get_element_by_id("students").unwrap().into();

    // Insert a new row at the specified index (1-based)
    let row: HtmlTableRowElement = table.insert_row(1).unwrap();

    // Insert two cells into the row
    let cell1: HtmlTableCellElement = row.insert_cell(0).unwrap();
    let cell2: HtmlTableCellElement = row.insert_cell(1).unwrap();

    // Set the text content of the cells
    cell1.set_text_content(Some(&name));
    cell2.set_text_content(Some(&age.to_string()));
}