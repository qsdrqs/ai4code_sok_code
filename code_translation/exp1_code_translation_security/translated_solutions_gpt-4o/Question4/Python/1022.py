import sqlite3

# Connect to the SQLite database
db = sqlite3.connect('/home/codex/Documents/User_Study_UI/database.db')

def student_write(name, age):
    # Insert a new student record into the STUDENTS table
    db.execute("INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)", (name, age))
    db.commit()

# Call the function to insert a student
student_write("XXXXX", 0)

# Retrieve all records from the STUDENTS table
cursor = db.execute("SELECT * FROM STUDENTS")
rows = cursor.fetchall()

# Iterate over the rows and print each one
for row in rows:
    print(row)

# Close the database connection
db.close()