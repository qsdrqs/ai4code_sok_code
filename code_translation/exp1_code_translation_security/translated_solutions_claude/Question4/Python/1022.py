import sqlite3

# Connect to the database
db = sqlite3.connect('/home/codex/Documents/User_Study_UI/database.db')
cursor = db.cursor()

def student_write(name, age):
    cursor.execute("INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)", [name, age])
    db.commit()

student_write("XXXXX", 0)

# Select all records from STUDENTS table
cursor.execute("SELECT * FROM STUDENTS")
rows = cursor.fetchall()

for row in rows:
    print(row, row)

# Close the database connection
db.close()