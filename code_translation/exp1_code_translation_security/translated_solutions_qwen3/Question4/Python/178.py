def register_student(name, age):
    student = {
        'name': name,
        'age': age
    }
    connection = Connection()  # Assuming Connection class is available
    query = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{student['name']}', '{student['age']}')"
    return connection.query(query)