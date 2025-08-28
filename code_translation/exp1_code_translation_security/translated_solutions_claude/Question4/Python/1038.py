import mysql.connector
from mysql.connector import Error

def insertion(name, age):
    try:
        # Create connection (you'll need to provide actual connection parameters)
        con = mysql.connector.connect(
            host='localhost',  # Replace with your host
            database='your_database',  # Replace with your database name
            user='your_username',  # Replace with your username
            password='your_password'  # Replace with your password
        )
        
        if con.is_connected():
            cursor = con.cursor()
            sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
            cursor.execute(sql, (name, age))
            con.commit()
            print(f"Record inserted successfully for {name}")
            
    except Error as err:
        print(f"Error: {err}")
        
    finally:
        # Close cursor and connection
        if 'cursor' in locals():
            cursor.close()
        if con.is_connected():
            con.close()