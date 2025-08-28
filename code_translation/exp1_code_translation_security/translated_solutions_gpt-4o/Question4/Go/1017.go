package main

import (
	"html/template"
	"net/http"
	"strconv"
)

// Template for rendering the HTML page
var tmpl = `
<!DOCTYPE html>
<html>
<head>
    <title>Students</title>
</head>
<body>
    <table id="students" border="1">
        <tr>
            <th>Name</th>
            <th>Age</th>
        </tr>
        {{range .}}
        <tr>
            <td>{{.Name}}</td>
            <td>{{.Age}}</td>
        </tr>
        {{end}}
    </table>
    <form method="POST" action="/">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
        <label for="age">Age:</label>
        <input type="number" id="age" name="age" required>
        <button type="submit">Add Student</button>
    </form>
</body>
</html>
`

// Student struct to hold student data
type Student struct {
	Name string
	Age  int
}

// Slice to store students
var students []Student

func handler(w http.ResponseWriter, r *http.Request) {
	// Handle form submission
	if r.Method == http.MethodPost {
		name := r.FormValue("name")
		ageStr := r.FormValue("age")
		age, err := strconv.Atoi(ageStr)
		if err == nil {
			// Add new student to the list
			students = append(students, Student{Name: name, Age: age})
		}
	}

	// Render the HTML template with the students data
	t := template.Must(template.New("students").Parse(tmpl))
	t.Execute(w, students)
}

func main() {
	http.HandleFunc("/", handler)
	http.ListenAndServe(":8080", nil)
}