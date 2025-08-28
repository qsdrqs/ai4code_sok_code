def addStudent(name, age):
    table = document.getElementById("students")
    row = table.insertRow(1)
    cell1 = row.insertCell(0)
    cell2 = row.insertCell(1)
    cell1.innerHTML = name
    cell2.innerHTML = age