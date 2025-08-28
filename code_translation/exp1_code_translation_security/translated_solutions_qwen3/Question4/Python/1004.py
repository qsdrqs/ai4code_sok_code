import mysql.connector
from mysql.connector import Error

def add_student(name, age):
    """
    Constructs an SQL INSERT statement for the STUDENTS table.
    """
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    print(sql)
    return sql

# Establish database connection
try:
    con = mysql.connector.connect(
        host="localhost",
        user="yourusername",
        password="yourpassword"
    )
except Error as err:
    print(f"Connection error: {err}")
    exit(1)

print("Connected!")

# Create a cursor object
cursor = con.cursor()

# Execute the SQL query
try:
    cursor.execute(add_student("XXXXX", "0"))  # age passed as string, mirroring JS example
except Error as err:
    print(f"Query error: {err}")
    exit(1)

# Log the result (number of affected rows)
print(f"Result: {cursor.rowcount}")