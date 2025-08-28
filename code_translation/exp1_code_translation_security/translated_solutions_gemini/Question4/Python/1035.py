import sqlite3

# 1. Connect to the database (this will create the file if it doesn't exist)
# The connection object is analogous to the 'db' object in the JS code.
conn = sqlite3.connect('studentdb')

# 2. Create a cursor object to execute SQL commands
cursor = conn.cursor()

# 3. Create the table if it doesn't already exist
# Note: SQLite's standard type for integers is INTEGER
cursor.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INTEGER)")

def insert(name, age):
  """Inserts a new student into the STUDENTS table."""
  # Use '?' as a placeholder to prevent SQL injection.
  # The second argument to execute() is a tuple of values to substitute.
  cursor.execute("INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)", (name, age))
  
  # Commit the transaction to save the changes to the database.
  conn.commit()
  
  # The commented-out section from the JS code would look like this in Python:
  # print("Current data in the table:")
  # for row in cursor.execute("SELECT * FROM STUDENTS"):
  #   print(row)

# Insert the data
insert("Foo", 42)
insert("Bar", 55)

print("Data inserted successfully.")

# Close the database connection
conn.close()