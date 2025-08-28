import mysql.connector
from mysql.connector import Error

def add_student(name, age):
    try:
        # Assuming 'connection' is a global variable or passed as parameter
        # If connection needs to be established, uncomment the following lines:
        # connection = mysql.connector.connect(
        #     host='localhost',
        #     database='your_database',
        #     user='your_username',
        #     password='your_password'
        # )
        
        sql = "INSERT INTO STUDENTS (name, age) VALUES (%s, %s)"
        cursor = connection.cursor()
        cursor.execute(sql, (name, age))
        connection.commit()
        print("1 record inserted")
        cursor.close()
        
    except Error as err:
        raise err