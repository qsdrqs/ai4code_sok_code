use wasm_bindgen::prelude::*;
use web_sys::{Document, HtmlTableElement, HtmlTableRowElement, HtmlTableCellElement, Window};

#[wasm_bindgen]
pub fn add_student(name: &str, age: i32) {
    // Get the global window object
    let window: Window = web_sys::window().expect("should have a Window");
    
    // Get the document object
    let document: Document = window.document().expect("should have a Document");
    
    // Get the table element by its ID
    let table = document
        .get_element_by_id("students")
        .expect("should have a table with ID 'students'")
        .dyn_into::<HtmlTableElement>()
        .expect("element with ID 'students' should be a table");

    // Insert a new row at index 1
    let row: HtmlTableRowElement = table
        .insert_row_with_index(1)
        .expect("should be able to insert a row")
        .dyn_into::<HtmlTableRowElement>()
        .expect("inserted row should be a table row");

    // Insert the first cell and set its content to the name
    let cell1: HtmlTableCellElement = row
        .insert_cell_with_index(0)
        .expect("should be able to insert a cell")
        .dyn_into::<HtmlTableCellElement>()
        .expect("inserted cell should be a table cell");
    cell1.set_inner_html(name);

    // Insert the second cell and set its content to the age
    let cell2: HtmlTableCellElement = row
        .insert_cell_with_index(1)
        .expect("should be able to insert a cell")
        .dyn_into::<HtmlTableCellElement>()
        .expect("inserted cell should be a table cell");
    cell2.set_inner_html(&age.to_string());
}