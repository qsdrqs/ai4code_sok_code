import sqlite3

# In Python, the standard library for SQLite is `sqlite3`.
# It's the equivalent of `require('sqlite3')`.

# Create a connection to an in-memory database.
# This is equivalent to `new sqlite3.Database(':memory:')`.
conn = sqlite3.connect(':memory:')

# To make the output rows behave like dictionaries (similar to JS objects),
# we can set the connection's row_factory.
conn.row_factory = sqlite3.Row

# In Python's sqlite3, you use a "cursor" object to execute commands.
cursor = conn.cursor()

# The `db.serialize` is not needed in Python because database operations
# using a single connection are executed sequentially by default.
cursor.execute("CREATE TABLE students (name TEXT, age INT)")

def insert_student(db_conn, name, age):
  """
  Inserts a new student into the database.
  
  Note: Python's DB-API uses '?' as the placeholder for parameters
  to prevent SQL injection, instead of '$name'.
  """
  # We get a cursor from the connection to execute the command.
  c = db_conn.cursor()
  c.execute("INSERT INTO students (name, age) VALUES (?, ?)", (name, age))
  
  # In Python, you must explicitly commit the transaction to save changes.
  db_conn.commit()


# Insert a student record
insert_student(conn, "XXXXX", 0)

# To fetch data, you execute a SELECT statement and then iterate over the cursor.
# This is the equivalent of `db.each`.
print("Fetching students from the database:")
for row in cursor.execute("SELECT * FROM students"):
  # Because we set conn.row_factory = sqlite3.Row, we can access columns
  # by name, just like in the JavaScript example.
  # We convert it to a dict for a cleaner print output.
  print(dict(row))

# Close the database connection.
conn.close()