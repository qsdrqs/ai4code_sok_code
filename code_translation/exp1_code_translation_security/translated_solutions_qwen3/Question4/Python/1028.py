def addStudent(name, age):
    sql = "INSERT INTO STUDENTS (name, age) VALUES (%s, %s)"
    cursor = connection.cursor()
    cursor.execute(sql, (name, age))
    connection.commit()
    print("1 record inserted")