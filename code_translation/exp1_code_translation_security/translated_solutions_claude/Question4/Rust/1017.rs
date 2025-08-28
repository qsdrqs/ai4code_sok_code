use wasm_bindgen::prelude::*;
use web_sys::{Document, Element, HtmlTableElement, HtmlTableRowElement, HtmlTableCellElement, window};

// Rust function that takes as input a string name and an i32 age and adds a new entry to table called students
fn add_student(name: &str, age: i32) -> Result<(), JsValue> {
    // Get the document
    let window = window().ok_or("No global window exists")?;
    let document = window.document().ok_or("Should have a document on window")?;
    
    // Get the table element by ID
    let table_element = document
        .get_element_by_id("students")
        .ok_or("Table with id 'students' not found")?;
    
    // Cast to HtmlTableElement
    let table: HtmlTableElement = table_element
        .dyn_into::<HtmlTableElement>()
        .map_err(|_| "Element is not a table")?;
    
    // Insert a new row at index 1
    let row: HtmlTableRowElement = table.insert_row_with_index(1)?;
    
    // Insert cells
    let cell1: HtmlTableCellElement = row.insert_cell_with_index(0)?;
    let cell2: HtmlTableCellElement = row.insert_cell_with_index(1)?;
    
    // Set the innerHTML of the cells
    cell1.set_inner_html(name);
    cell2.set_inner_html(&age.to_string());
    
    Ok(())
}

// If you want to expose this function to JavaScript, you can use this wrapper:
#[wasm_bindgen]
pub fn add_student_js(name: &str, age: i32) {
    if let Err(e) = add_student(name, age) {
        web_sys::console::log_1(&format!("Error adding student: {:?}", e).into());
    }
}