use wasm_bindgen::prelude::*;
use web_sys::{Document, Element, HtmlTableElement, HtmlTableRowElement};

#[wasm_bindgen]
pub fn add_student(name: &str, age: i32) {
    // Get the global window object
    let window = web_sys::window().expect("global window does not exist");

    // Get the document object
    let document: Document = window.document().expect("document does not exist");

    // Get the table element by ID
    let table_element: Element = document
        .get_element_by_id("students")
        .expect("element with id 'students' not found");

    // Cast the Element to an HtmlTableElement
    let table: HtmlTableElement = table_element
        .dyn_into::<HtmlTableElement>()
        .expect("element is not a table");

    // Insert a new row at index 1
    let row: HtmlTableRowElement = table
        .insert_row_with_index(1)
        .expect("failed to insert row");

    // Insert two cells into the row
    let cell1 = row
        .insert_cell_with_cell_index(0)
        .expect("failed to insert cell 0");

    let cell2 = row
        .insert_cell_with_cell_index(1)
        .expect("failed to insert cell 1");

    // Set the inner HTML of the cells
    cell1.set_inner_html(name);
    cell2.set_inner_html(&age.to_string());
}