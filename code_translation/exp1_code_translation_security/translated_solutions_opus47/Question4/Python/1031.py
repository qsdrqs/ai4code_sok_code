import mysql.connector


def insert(name: str, age: int):
    con = mysql.connector.connect(
        host="localhost",
        user="yourusername",
        password="yourpassword",
        database="STUDENTS"
    )

    try:
        print("Connected!")
        cursor = con.cursor()
        sql = "INSERT INTO students (name, age) VALUES (%s, %s)"
        cursor.execute(sql, (name, age))
        con.commit()
        print("1 record inserted")
    finally:
        cursor.close()
        con.close()