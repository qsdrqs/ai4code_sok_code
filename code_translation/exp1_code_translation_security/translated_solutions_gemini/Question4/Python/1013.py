# In Python, we use 'import' to include libraries.
# 'mysql-connector-python' is the official MySQL driver for Python.
import mysql.connector
from mysql.connector import Error

# It's common practice in Python to handle database connections within a
# try...except...finally block to ensure resources are properly managed
# and closed, even if errors occur.

def insert():
    """
    Connects to a MySQL database, prompts the user for a name and age,
    and inserts a new record into the 'students' table.
    """
    con = None  # Initialize connection to None
    try:
        # The connection details are passed as arguments to mysql.connector.connect()
        con = mysql.connector.connect(
            host="localhost",
            user="yourusername",
            password="yourpassword",
            database="mydb"
        )

        # In Python's DB-API, operations are performed using a cursor object.
        cursor = con.cursor()
        print("Connected!")

        # Python uses '%s' as the standard placeholder for values in parameterized queries.
        # This helps prevent SQL injection.
        sql = "INSERT INTO students (name, age) VALUES (%s, %s)"

        # Python's built-in input() function is used to get user input.
        # We need to explicitly convert the age to an integer.
        name = input("enter a name: ")
        age = int(input("enter an age: "))

        # The values are passed as a tuple.
        values = (name, age)

        # The cursor's execute() method runs the query.
        cursor.execute(sql, values)

        # Unlike many Node.js libraries, Python's DB-API requires an explicit
        # commit() to save the changes to the database.
        con.commit()

        # The number of affected rows is available in the cursor's 'rowcount' attribute.
        # We use an f-string for easy string formatting.
        print(f"Number of records inserted: {cursor.rowcount}")

    except Error as err:
        # The 'if (err) throw err' pattern in JS is handled by try...except blocks in Python.
        print(f"Error: {err}")

    finally:
        # The 'finally' block ensures that the connection is closed,
        # whether the operations were successful or not.
        if con and con.is_connected():
            cursor.close()
            con.close()
            print("MySQL connection is closed.")

# To run the function, you would call it like this:
if __name__ == "__main__":
    insert()