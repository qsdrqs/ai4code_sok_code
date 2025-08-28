import mysql.connector
from mysql.connector import Error

# Assuming connection is a mysql.connector connection object
def add_student(name, age):
    q = """INSERT INTO STUDENTS(NAME, AGE)
           VALUES(%s, %s)"""
    v = [name, age]
    
    try:
        cursor = connection.cursor()
        cursor.execute(q, v)
        connection.commit()
        cursor.close()
    except Error as err:
        pass  # Error handling (equivalent to empty callback in original JS)