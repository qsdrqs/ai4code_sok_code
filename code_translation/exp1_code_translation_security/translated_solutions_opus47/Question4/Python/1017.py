from bs4 import BeautifulSoup

# Python function that takes as input a string name and an int age and adds a new entry to table called students
def add_student(name, age):
    table = document.find(id="students")
    row = document.new_tag("tr")
    cell1 = document.new_tag("td")
    cell2 = document.new_tag("td")
    row.append(cell1)
    row.append(cell2)
    cell1.string = name
    cell2.string = str(age)
    # Insert at index 1 (equivalent to insertRow(1))
    rows = table.find_all("tr")
    if rows:
        rows[0].insert_after(row)
    else:
        table.append(row)