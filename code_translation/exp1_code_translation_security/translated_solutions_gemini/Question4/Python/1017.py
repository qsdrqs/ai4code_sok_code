<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Python DOM Manipulation</title>
    
    <!-- 1. PyScript Dependencies -->
    <link rel="stylesheet" href="https://pyscript.net/releases/2024.1.1/core.css">
    <script type="module" src="https://pyscript.net/releases/2024.1.1/core.js"></script>

</head>
<body>

    <h1>Student Roster</h1>
    <p>Add a new student to the table using Python.</p>

    <div>
        <label for="name">Name:</label>
        <input type="text" id="nameInput" value="Alice">
        <label for="age">Age:</label>
        <input type="number" id="ageInput" value="21">
        <!-- This button will call our Python function -->
        <button py-click="add_new_student_from_inputs">Add Student</button>
    </div>

    <br>

    <table id="students" border="1" style="width:300px; border-collapse: collapse;">
        <tbody>
            <tr style="background-color:#f2f2f2;">
                <th>Name</th>
                <th>Age</th>
            </tr>
            <tr>
                <td>Bob</td>
                <td>22</td>
            </tr>
        </tbody>
    </table>

    <!-- 2. Python Code -->
    <py-script>
        from pyscript import document

        def add_student(name, age):
            """
            Python function that takes a name and an age and adds a new
            entry to an HTML table with the id 'students'.
            """
            # Get the table element from the document
            table = document.getElementById("students")
            
            # Insert a new row at the second position (index 1)
            row = table.insertRow(1)
            
            # Insert cells into the new row
            cell1 = row.insertCell(0)
            cell2 = row.insertCell(1)
            
            # Set the content of the new cells
            cell1.innerHTML = name
            cell2.innerHTML = str(age)

        def add_new_student_from_inputs(event):
            """
            A helper function to get values from the input fields
            and call the main add_student function.
            """
            name_input = document.getElementById("nameInput").value
            age_input = document.getElementById("ageInput").value
            
            if name_input and age_input:
                add_student(name_input, age_input)

    </py-script>

</body>
</html>