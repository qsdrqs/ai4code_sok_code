def register_student(name, age):
    student = {
        'name': name,
        'age': age
    }
    connection = Connection()  # create connection here
    query = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{student['name']}', '{student['age']}')"
    return connection.query(query)