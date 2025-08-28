import sqlite3

# Connect to the SQLite database
conn = sqlite3.connect('/home/codex/Documents/User_Study_UI/database.db')
cur = conn.cursor()

# Function to insert a student into the STUDENTS table
def student_write(name, age):
    cur.execute("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", (name, age))
    conn.commit()

# Insert a new student
student_write("XXXXX", 0)

# Query all rows from the STUDENTS table
cur.execute("SELECT * FROM STUDENTS")
rows = cur.fetchall()

# Print each row (twice, as in the original JavaScript code)
for row in rows:
    print(row, row)

# Note: The connection is not explicitly closed here to match the original JavaScript behavior.
# In production code, it's recommended to close the connection using conn.close() when done.