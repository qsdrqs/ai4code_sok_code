import sqlite3
import os

# The path to the database file from the original code
# Note: You might need to adjust this path depending on your directory structure.
db_path = '/home/codex/Documents/User_Study_UI/database.db'

# --- Start of Translation ---

# 1. Connect to the SQLite database.
# The database will be created if it doesn't exist.
conn = sqlite3.connect(db_path)

# Create a cursor object to execute SQL commands
cursor = conn.cursor()

# (Optional) Create the table if it doesn't exist to make the script runnable
# This part is added for completeness, assuming the table might not exist.
cursor.execute('''
CREATE TABLE IF NOT EXISTS STUDENTS (
    ID   INTEGER PRIMARY KEY AUTOINCREMENT,
    NAME TEXT    NOT NULL,
    AGE  INTEGER NOT NULL
);
''')

def student_write(name, age):
  """
  Inserts a new student record into the STUDENTS table.
  """
  # Use '?' as placeholders to prevent SQL injection
  cursor.execute("INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)", (name, age))
  # Commit the transaction to save the changes
  conn.commit()

# Call the function to insert a new record
student_write("XXXXX", 0)

# Select all records from the STUDENTS table
print("Fetching all students from the database:")
cursor.execute("SELECT * FROM STUDENTS")

# fetchall() retrieves all rows from the query result
rows = cursor.fetchall()

# Loop through the list of rows (which are tuples)
for row in rows:
    # Print each row twice, just like the original console.log(row, row)
    print(row, row)

# Close the database connection
conn.close()