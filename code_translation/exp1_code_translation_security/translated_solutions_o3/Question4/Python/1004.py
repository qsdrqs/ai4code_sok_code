# Python translation of the original JavaScript snippet
# (Assumes that the required MySQL driver is available.)

import mysql.connector  # Dependency will be available

def add_student(name: str, age: int) -> str:
    """
    Build and return an INSERT statement for the STUDENTS table.
    Mimics console.log in JavaScript by printing the SQL string.
    """
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    print(sql)
    return sql


# Establish a connection to the MySQL server
connection = mysql.connector.connect(
    host="localhost",
    user="yourusername",
    password="yourpassword"
)

print("Connected!")

# Create a cursor, run the query, commit, and log the result
cursor = connection.cursor()
try:
    cursor.execute(add_student("XXXXX", 0))
    connection.commit()  # Persist the insertion
    print("Result:", cursor.rowcount)  # Number of rows affected
finally:
    cursor.close()
    connection.close()