import sqlite3

def add_student_secure(db_connection, name, age):
  """
  Inserts a new student into the database using a secure parameterized query.

  This is the recommended, secure way to execute SQL queries in Python.
  It prevents SQL injection attacks.

  Args:
      db_connection: An active database connection object.
      name (str): The student's name.
      age (int): The student's age.
  """
  # The SQL query uses '?' as a placeholder for data.
  # The database driver will safely substitute the data.
  sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
  
  try:
    # The cursor is used to execute commands.
    cursor = db_connection.cursor()
    
    # Execute the command, passing the data as a separate tuple.
    cursor.execute(sql, (name, age))
    
    # Commit the transaction to save the changes to the database.
    db_connection.commit()
    
    print(f"Successfully added student: {name}")
  except sqlite3.Error as e:
    print(f"Database error: {e}")
    db_connection.rollback() # Roll back changes if something went wrong


# --- Full, runnable example of how to use the secure function ---

# This part sets up an in-memory database for the example.
# In a real application, you would connect to a file: conn = sqlite3.connect('students.db')
conn = sqlite3.connect(':memory:')

# Create the table first
cursor = conn.cursor()
cursor.execute("""
CREATE TABLE STUDENTS (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    NAME TEXT NOT NULL,
    AGE INTEGER NOT NULL
)
""")
conn.commit()

# Now, call the secure function to add students
add_student_secure(conn, "Bob", 22)
add_student_secure(conn, "Cathy", 21)

# Example of a malicious input that is now completely safe.
# The name will be inserted literally, not executed as a command.
malicious_name = "Robert'); DROP TABLE STUDENTS; --"
add_student_secure(conn, malicious_name, 30)

# Verify the data was inserted correctly
print("\n--- Verifying contents of the STUDENTS table ---")
for row in cursor.execute("SELECT NAME, AGE FROM STUDENTS ORDER BY ID"):
    print(row)

# Close the connection when done
conn.close()