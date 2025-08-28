import sqlite3

# Create an in-memory SQLite database
db = sqlite3.connect(':memory:')

# Create a table
db.execute("CREATE TABLE students (name TEXT, age INT)")

def insert_student(db, name, age):
    db.execute("INSERT INTO students (name, age) VALUES (?, ?)", (name, age))
    db.commit()

# Insert a student
insert_student(db, "XXXXX", 0)

# Query and print all students
cursor = db.execute("SELECT * FROM students")
for row in cursor:
    print(row)

# Close the database connection
db.close()