import mysql.connector
from mysql.connector import Error

# In Python, the connection is typically established once and reused.
# The arguments are passed to the connect function.
try:
    con = mysql.connector.connect(
        # host="your_host",
        # user="your_username",
        # password="your_password",
        # database="your_database"
        # Add your connection details here...
    )
except Error as e:
    print(f"Error connecting to MySQL: {e}")
    # Exit or handle the connection error appropriately
    raise

def add_student(name: str, age: int):
  """
  Inserts a new student record into the STUDENTS table.

  Args:
    name (str): The name of the student.
    age (int): The age of the student.
  """
  # The SQL query uses %s as placeholders for values.
  # This is crucial for preventing SQL injection vulnerabilities.
  # It's also best practice to specify the columns you are inserting into.
  query = "INSERT INTO STUDENTS (name, age) VALUES (%s, %s)"
  
  # The values are passed as a tuple to the execute method.
  values = (name, age)
  
  cursor = None
  try:
    # A cursor is used to execute queries
    cursor = con.cursor()
    cursor.execute(query, values)
    
    # The connection.commit() method is used to save the changes.
    con.commit()
    
    print(f"{cursor.rowcount} record inserted successfully into STUDENTS table.")
    
  except Error as e:
    print(f"Failed to insert record into STUDENTS table: {e}")
    
  finally:
    # It's important to close the cursor after you're done with it.
    if cursor:
        cursor.close()

# Example usage:
# add_student("Alice", 22)
# add_student("Bob", 25)

# Don't forget to close the connection when your application is done.
# con.close()