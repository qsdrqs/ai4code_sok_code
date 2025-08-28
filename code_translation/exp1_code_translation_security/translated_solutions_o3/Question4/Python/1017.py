# Python (PyScript / Pyodide) equivalent of the JavaScript snippet above.
# The “document” object and every other dependency will be available in
# the execution environment, so we can interact with the DOM exactly as
# in JavaScript.

from js import document  # Provided by the environment

def add_student(name: str, age: int) -> None:
    """
    Insert a new row into the HTML table whose id is "students".
    The row is placed at index 1 (immediately after the header)
    and contains two cells: the student's name and age.
    """
    table = document.getElementById("students")

    # Create a new row in position 1
    row = table.insertRow(1)

    # Create the two cells
    cell_name = row.insertCell(0)
    cell_age  = row.insertCell(1)

    # Populate the cells
    cell_name.innerHTML = name
    cell_age.innerHTML  = age