import sqlite3
import sys

# Assuming there's a SQL table called STUDENTS available

def add(name, age):
    """
    adds a new entry to SQL table "STUDENTS"
    """
    # Using parameterized query to prevent SQL injection
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    print(f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})")
    
    try:
        cursor = connection.cursor()
        cursor.execute(sql, (name, age))
        connection.commit()
        print("1 record inserted")
    except Exception as err:
        print(f"Error: {err}")
        sys.exit(1)

# Example of how to create a connection (you would need to initialize this)
# connection = sqlite3.connect('your_database.db')