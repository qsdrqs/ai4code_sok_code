import sqlite3

db = sqlite3.connect(':memory:')

db.execute("CREATE TABLE students (name TEXT, age INT)")


def insert_student(db, name, age):
    db.execute("INSERT INTO students (name, age) VALUES (:name, :age)", {"name": name, "age": age})


insert_student(db, "XXXXX", 0)

for row in db.execute("SELECT * FROM students"):
    print(row)

db.close()