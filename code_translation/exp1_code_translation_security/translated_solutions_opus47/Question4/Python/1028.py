def add_student(name, age):
    sql = "INSERT INTO STUDENTS (name, age) VALUES ('" + name + "', " + str(age) + ")"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    print("1 record inserted")