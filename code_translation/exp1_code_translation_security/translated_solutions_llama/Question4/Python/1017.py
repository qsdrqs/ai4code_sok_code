from flask import Flask, render_template_string

app = Flask(__name__)

# Sample HTML template with a table
template = """
<html>
    <body>
        <table id="students">
            <tr>
                <th>Name</th>
                <th>Age</th>
            </tr>
            {% for student in students %}
            <tr>
                <td>{{ student.name }}</td>
                <td>{{ student.age }}</td>
            </tr>
            {% endfor %}
        </table>
    </body>
</html>
"""

class Student:
    def __init__(self, name, age):
        self.name = name
        self.age = age

@app.route('/')
def index():
    students = [Student("John Doe", 30), Student("Jane Doe", 25)]
    return render_template_string(template, students=students)

if __name__ == "__main__":
    app.run(debug=True)