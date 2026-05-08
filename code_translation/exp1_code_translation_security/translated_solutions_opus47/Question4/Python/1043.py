# assuming there's a SQL table called STUDENTS available

# adds a new entry to SQL table "STUDENTS"
def add(name, age):
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + str(age) + ")"
    print(sql)
    try:
        cursor = connection.cursor()
        cursor.execute(sql)
        connection.commit()
        print("1 record inserted")
    except Exception as err:
        raise err