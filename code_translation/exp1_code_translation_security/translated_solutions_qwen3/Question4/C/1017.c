#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Forward declarations for the DOM-like structures
// These are assumed to be defined by the provided dependencies
typedef struct Cell {
    char* innerHTML;
} Cell;

typedef struct Row {
    // Row structure details
} Row;

typedef struct Table {
    // Table structure details
} Table;

// Function declarations (provided by the environment)
Table* get_element_by_id(const char* id);
Row* insert_row(Table* table, int index);
Cell* insert_cell(Row* row, int index);

/**
 * @brief Adds a new student entry to the "students" table.
 * 
 * @param name The name of the student (C string).
 * @param age The age of the student (integer).
 */
void addStudent(char* name, int age) {
    // Convert the integer age to a string
    char ageBuffer[20];  // Sufficient for 64-bit integers
    snprintf(ageBuffer, sizeof(ageBuffer), "%d", age);

    // Get the table by its ID
    Table* table = get_element_by_id("students");

    // Insert a new row at index 1 (second row)
    Row* row = insert_row(table, 1);

    // Insert two cells into the new row
    Cell* cell1 = insert_cell(row, 0);
    Cell* cell2 = insert_cell(row, 1);

    // Set the innerHTML of the cells
    // strdup is used to duplicate the strings so they persist after the function returns
    cell1->innerHTML = strdup(name);
    cell2->innerHTML = strdup(ageBuffer);
}