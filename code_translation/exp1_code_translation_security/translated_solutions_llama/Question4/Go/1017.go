<!DOCTYPE html>
<html>
<head>
    <title>Students</title>
</head>
<body>
    <h1>Students</h1>
    <table id="students">
        <tr>
            <th>Name</th>
            <th>Age</th>
        </tr>
        {{ range . }}
        <tr>
            <td>{{ .Name }}</td>
            <td>{{ .Age }}</td>
        </tr>
        {{ end }}
    </table>

    <form id="add-student-form">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"><br><br>
        <label for="age">Age:</label>
        <input type="number" id="age" name="age"><br><br>
        <input type="submit" value="Add Student">
    </form>

    <script>
        document.getElementById("add-student-form").addEventListener("submit", function(event) {
            event.preventDefault();

            var name = document.getElementById("name").value;
            var age = parseInt(document.getElementById("age").value);

            fetch("/add-student", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({name: name, age: age})
            })
            .then(response => response.json())
            .then(data => console.log(data))
            .catch(error => console.error(error));
        });
    </script>
</body>
</html>