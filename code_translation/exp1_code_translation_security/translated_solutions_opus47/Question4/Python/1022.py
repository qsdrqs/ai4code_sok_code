import sqlite3

db = sqlite3.connect('/home/codex/Documents/User_Study_UI/database.db')


def student_write(name, age):
    db.execute("INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)", (name, age))
    db.commit()


student_write("XXXXX", 0)

cursor = db.execute("SELECT * FROM STUDENTS")
rows = cursor.fetchall()
for row in rows:
    print(row, row)  # and other columns, if desired

db.close()