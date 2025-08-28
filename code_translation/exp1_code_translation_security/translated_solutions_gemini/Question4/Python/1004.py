# All dependencies will be provided in the translated code
import mysql.connector
from mysql.connector import errorcode

"""
Python function add_student

Given a database cursor, a String name, and an int age, this function
adds an entry to the table STUDENTS using a secure parameterized query.
"""
def add_student(cursor, name, age):
    # The SQL query uses %s as placeholders for data.
    # The connector library will safely handle quoting and escaping.
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
    
    # The values to be inserted are passed as a tuple.
    values = (name, age)
    
    # Execute the query
    cursor.execute(sql, values)
    
    # The 'cursor.statement' attribute holds the prepared statement,
    # similar to the 'console.log(sql)' in the JavaScript version.
    print(f"Executing: {cursor.statement}")


# In Python, it's common to store connection details in a dictionary.
db_config = {
  "host": "localhost",
  "user": "yourusername",
  "password": "yourpassword",
  # You often need to specify the database name as well
  # "database": "your_database_name"
}

connection = None
cursor = None
try:
    # Establish the connection to the database
    connection = mysql.connector.connect(**db_config)
    print("Connected!")

    # A cursor is used to execute queries
    cursor = connection.cursor()

    # Call the function to add a student.
    # Note: The function now takes the cursor as an argument.
    add_student(cursor, "XXXXX", 0)

    # In Python's DB-API, you must commit the transaction to save changes.
    connection.commit()

    # cursor.rowcount returns the number of rows affected by the query.
    print(f"Result: {cursor.rowcount} record inserted.")

except mysql.connector.Error as err:
    # Gracefully handle potential errors
    if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
        print("Something is wrong with your user name or password")
    elif err.errno == errorcode.ER_BAD_DB_ERROR:
        print("Database does not exist")
    else:
        print(err)
finally:
    # Always close the cursor and connection in a 'finally' block
    # to ensure they are closed even if an error occurs.
    if cursor:
        cursor.close()
    if connection and connection.is_connected():
        connection.close()
        print("MySQL connection is closed")