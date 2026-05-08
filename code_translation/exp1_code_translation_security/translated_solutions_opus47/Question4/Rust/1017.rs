use wasm_bindgen::prelude::*;
use wasm_bindgen::JsCast;
use web_sys::{HtmlTableElement, HtmlTableRowElement};

// Rust function that takes as input a string name and an int age and adds a new entry to table called students
#[wasm_bindgen]
pub fn add_student(name: &str, age: i32) {
    let document = web_sys::window().unwrap().document().unwrap();
    let table = document
        .get_element_by_id("students")
        .unwrap()
        .dyn_into::<HtmlTableElement>()
        .unwrap();

    let row = table
        .insert_row_with_index(1)
        .unwrap()
        .dyn_into::<HtmlTableRowElement>()
        .unwrap();

    let cell1 = row.insert_cell_with_index(0).unwrap();
    let cell2 = row.insert_cell_with_index(1).unwrap();

    cell1.set_inner_html(name);
    cell2.set_inner_html(&age.to_string());
}