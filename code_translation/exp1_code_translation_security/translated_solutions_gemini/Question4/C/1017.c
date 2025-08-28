#include <stdio.h>
#include <stdlib.h>

// An opaque struct to represent any HTML element.
// The internal details are hidden, just like in the browser's DOM.
typedef struct Element Element;

/**
 * @brief Finds an element by its ID. Corresponds to document.getElementById().
 * @param id The ID of the element to find.
 * @return A pointer to the Element, or NULL if not found.
 */
Element* getElementById(const char* id);

/**
 * @brief Inserts a new row into a table element. Corresponds to table.insertRow().
 * @param table_element A pointer to a table element.
 * @param index The position where the new row should be inserted.
 * @return A pointer to the newly created row Element.
 */
Element* insertRow(Element* table_element, int index);

/**
 * @brief Inserts a new cell into a row element. Corresponds to row.insertCell().
 * @param row_element A pointer to a row element.
 * @param index The position where the new cell should be inserted.
 * @return A pointer to the newly created cell Element.
 */
Element* insertCell(Element* row_element, int index);

/**
 * @brief Sets the content of an element. Corresponds to element.innerHTML = ...
 * @param element A pointer to the element to modify.
 * @param content The string content to set.
 */
void set_innerHTML(Element* element, const char* content);