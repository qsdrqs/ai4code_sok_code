def add_student(name, age):
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + str(age) + ")"
    try:
        cursor = connection.cursor()
        cursor.execute(sql)
        connection.commit()
        print("1 record inserted")
    except Exception as err:
        raise err