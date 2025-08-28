import mysql.connector
from mysql.connector import Error

# Assuming connection is already established
# connection = mysql.connector.connect(
#     host='localhost',
#     database='your_database',
#     user='your_username',
#     password='your_password'
# )

def add(name, age):
    """Add name, age to students table in MySQL"""
    query = "INSERT INTO students (name, age) VALUES (%s, %s)"
    
    try:
        cursor = connection.cursor()
        cursor.execute(query, (name, age))
        connection.commit()
        print(f"Added {name} to students table")
        cursor.close()
    except Error as err:
        raise err