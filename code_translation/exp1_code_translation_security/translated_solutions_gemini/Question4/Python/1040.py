import sqlite3

# In Python, the standard library for SQLite is `sqlite3`.
# We connect to the database, using ':memory:' for an in-memory database,
# just like in the JavaScript example.
conn = sqlite3.connect(':memory:')

# Setting a row_factory allows us to access columns by name,
# similar to the JavaScript object `row`.
conn.row_factory = sqlite3.Row

# We create a cursor object to execute SQL commands.
cursor = conn.cursor()

def add_new_entry(name, age):
  """
  Adds a new entry to the STUDENTS table.
  Python's sqlite3 library executes commands sequentially by default,
  so there is no need for an equivalent of `db.serialize()`.
  """
  # Create table if it doesn't exist.
  cursor.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT)")
  
  # Insert a new row. The '?' placeholders prevent SQL injection.
  # This is the Python equivalent of preparing and running a statement.
  cursor.execute("INSERT INTO STUDENTS VALUES (?, ?)", (name, age))
  
  # We commit the changes to the database.
  conn.commit()

# Add two entries to the database.
add_new_entry("XXXXX", 0)
add_new_entry("XXXXX", 0)

# Select all rows from the STUDENTS table.
cursor.execute("SELECT NAME, AGE FROM STUDENTS")

# In Python, we iterate over the cursor to get each row.
# This is the equivalent of Node.js's `db.each()`.
for row in cursor:
    # The row object from sqlite3.Row can be easily converted to a dictionary
    # to closely match the JavaScript console.log(row) output.
    print(dict(row))

# It's good practice to close the connection when done.
conn.close()