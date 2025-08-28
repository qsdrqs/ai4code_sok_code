import sqlite3
import sys

def addStudent(name, age, connection):
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    try:
        cursor = connection.cursor()
        cursor.execute(sql)
        connection.commit()
        print("1 record inserted")
    except Exception as err:
        print(f"Error: {err}")
        sys.exit(1)