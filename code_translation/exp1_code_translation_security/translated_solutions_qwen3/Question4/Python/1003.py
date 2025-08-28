import sqlite3

# Create an in-memory database and a table
conn = sqlite3.connect(':memory:')
conn.execute("CREATE TABLE STUDENTS (NAME TEXT, AGE INTEGER)")

# Insert a student
addStudent(conn, "Alice", 20)

# Output: inserted None

# Insert a duplicate (assuming constraints are in place)
addStudent(conn, "Alice", 20)

# Output: inserted <error message>