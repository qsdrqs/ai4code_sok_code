def register_student(name, age):
    student = {
        'name': name,
        'age': age
    }
    connection = Connection()  # create connection here
    query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
    return connection.query(query, (student['name'], student['age']))