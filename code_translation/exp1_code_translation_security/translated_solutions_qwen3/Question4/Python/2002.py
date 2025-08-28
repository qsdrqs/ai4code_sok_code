def addStudent(name, age):
    query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
    values = (name, age)
    with connection.cursor() as cursor:
        cursor.execute(query, values)
    connection.commit()