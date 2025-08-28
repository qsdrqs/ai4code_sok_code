import sqlite3

# Create an in-memory database
db = sqlite3.connect(':memory:')

# Create cursor for executing SQL commands
cursor = db.cursor()

# Create the students table
cursor.execute("CREATE TABLE students (name TEXT, age INT)")

def insertStudent(db, name, age):
    cursor = db.cursor()
    cursor.execute("INSERT INTO students (name, age) VALUES (?, ?)", (name, age))
    db.commit()

# Insert a student
insertStudent(db, "XXXXX", 0)

# Select and print all students
cursor.execute("SELECT * FROM students")
rows = cursor.fetchall()

for row in rows:
    print(row)

# Close the database connection
db.close()