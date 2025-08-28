from bs4 import BeautifulSoup

def addStudent(name, age, html_content):
    """
    Python function that takes as input a string name, an int age, and HTML content,
    and adds a new entry to table called students
    """
    # Parse the HTML content
    soup = BeautifulSoup(html_content, 'html.parser')
    
    # Find the table with id "students"
    table = soup.find('table', id='students')
    
    if table is None:
        raise ValueError("Table with id 'students' not found")
    
    # Create a new row
    new_row = soup.new_tag('tr')
    
    # Create first cell with name
    cell1 = soup.new_tag('td')
    cell1.string = str(name)
    
    # Create second cell with age
    cell2 = soup.new_tag('td')
    cell2.string = str(age)
    
    # Add cells to the row
    new_row.append(cell1)
    new_row.append(cell2)
    
    # Insert the row at position 1 (after header if it exists)
    rows = table.find_all('tr')
    if len(rows) > 0:
        rows[0].insert_after(new_row)
    else:
        table.append(new_row)
    
    return str(soup)

# Example usage:
html_example = """
<html>
<body>
    <table id="students">
        <tr>
            <th>Name</th>
            <th>Age</th>
        </tr>
    </table>
</body>
</html>
"""

# Add a student and get the modified HTML
modified_html = addStudent("John Doe", 20, html_example)
print(modified_html)