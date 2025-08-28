/*  add_student.c
 *
 *  C equivalent of the JavaScript addStudent function.
 *  Assumes the existence of a DOM-style C API (types Element, Row, Cell, …)
 *  whose headers / implementation will be supplied elsewhere.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*  Hypothetical DOM/HTML “glue” — supplied by caller.
 *  --------------------------------------------------
 *  Element *Document_getElementById(const char *id);
 *  Row     *Table_insertRow(Element *table, int index);
 *  Cell    *Row_insertCell(Row *row, int index);
 *  void     Cell_setInnerHTML(Cell *cell, const char *html);
 */
#include "dom.h"          /* <- Provided in the build environment */

/* ------------------------------------------------------------------------- */
void addStudent(const char *name, int age)
{
    /* 1. Fetch the table with id="students". */
    Element *table = Document_getElementById("students");
    if (table == NULL) {
        /* Handle error as appropriate. */
        fprintf(stderr, "Error: table \"students\" not found.\n");
        return;
    }

    /* 2. Insert a new row at index 1 (second visual row). */
    Row *row = Table_insertRow(table, 1);
    if (row == NULL) {
        fprintf(stderr, "Error: could not insert row.\n");
        return;
    }

    /* 3. Insert two cells in that row. */
    Cell *cell1 = Row_insertCell(row, 0);
    Cell *cell2 = Row_insertCell(row, 1);

    if (cell1 == NULL || cell2 == NULL) {
        fprintf(stderr, "Error: could not insert cells.\n");
        return;
    }

    /* 4. Populate the cells. */
    Cell_setInnerHTML(cell1, name);

    /* Convert integer age to string before assigning HTML. */
    char ageStr[16];                         /* plenty for any int */
    snprintf(ageStr, sizeof(ageStr), "%d", age);
    Cell_setInnerHTML(cell2, ageStr);
}