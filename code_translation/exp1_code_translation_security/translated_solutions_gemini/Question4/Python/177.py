import sqlite3

def add_student(name, age):
  """
  Inserts a new student record into the database.

  This function connects to an SQLite database, inserts a new student's name and age,
  and then closes the connection. It uses parameterized queries to prevent
  SQL injection attacks.

  Args:
    name (str): The name of the student.
    age (int): The age of the student.
  """
  db_file = 'school.db'
  # The SQL command uses '?' as placeholders for data.
  # This is the safe way to pass variables into a query.
  command = "INSERT INTO STUDENTS (name, age) VALUES (?, ?);"
  
  connection = None  # Initialize connection to None
  try:
    # The 'with' statement ensures the connection is automatically closed
    # even if errors occur.
    with sqlite3.connect(db_file) as connection:
      cursor = connection.cursor()
      # Execute the command, passing the variables as a tuple.
      # The database driver safely handles the values.
      cursor.execute(command, (name, age))
      # Commit the transaction to save the changes to the database.
      connection.commit()
      print(f"Successfully added student: {name}")
  except sqlite3.Error as e:
    print(f"An error occurred: {e}")

# --- Example Usage ---

def setup_database():
  """A helper function to create the database and table for the example."""
  db_file = 'school.db'
  create_table_sql = """
  CREATE TABLE IF NOT EXISTS STUDENTS (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      name TEXT NOT NULL,
      age INTEGER NOT NULL
  );
  """
  try:
    with sqlite3.connect(db_file) as connection:
      cursor = connection.cursor()
      cursor.execute(create_table_sql)
      connection.commit()
    print("Database and STUDENTS table are ready.")
  except sqlite3.Error as e:
    print(f"An error occurred during setup: {e}")

if __name__ == '__main__':
  # 1. Set up the database and table first.
  setup_database()
  
  # 2. Now, add students using the translated function.
  print("\nAdding students...")
  add_student('Alice', 22)
  add_student('Bob', 25)
  
  # This demonstrates the security risk in the original code.
  # In the old code, a name like this could be used to drop the table.
  # In our secure Python code, it will be safely inserted as a string.
  add_student("Robert'); DROP TABLE STUDENTS; --", 30)