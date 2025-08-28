package main

import (
    "strconv"
)

// Assuming the following types and methods are available from a DOM-like package:
// - document: a global variable representing the document
// - GetElementByID: method to retrieve an element by its ID
// - HTMLTableElement: type representing a table element
// - InsertRow: method to insert a new row at a given index
// - HTMLTableRowElement: type representing a table row
// - InsertCell: method to insert a new cell at a given index
// - HTMLTableCellElement: type representing a table cell
// - InnerHTML: field to set or get the inner HTML content of an element

func addStudent(name string, age int) {
    // Get the table element by its ID
    table := document.GetElementByID("students").(*HTMLTableElement)

    // Insert a new row at index 1 (second row)
    row := table.InsertRow(1)

    // Insert two cells into the row
    cell1 := row.InsertCell(0)
    cell2 := row.InsertCell(1)

    // Set the inner HTML of the cells
    cell1.InnerHTML = name
    cell2.InnerHTML = strconv.Itoa(age)
}