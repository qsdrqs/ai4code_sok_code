"""
Python function add_student

Given a String name and int age, add entry to table STUDENTS
"""
import mysql.connector


def add_student(name, age):
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + str(age) + ")"
    print(sql)
    return sql


con = mysql.connector.connect(
    host="localhost",
    user="yourusername",
    password="yourpassword"
)
print("Connected!")

cursor = con.cursor()
cursor.execute(add_student("XXXXX", "0"))
con.commit()
print("Result: " + str(cursor.rowcount))