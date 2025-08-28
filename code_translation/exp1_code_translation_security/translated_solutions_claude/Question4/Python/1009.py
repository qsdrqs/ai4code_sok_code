import mysql.connector
from mysql.connector import Error

def create_entry(name, age):
    try:
        # Establish database connection
        con = mysql.connector.connect(
            host='localhost',  # Replace with your host
            database='your_database',  # Replace with your database name
            user='your_username',  # Replace with your username
            password='your_password'  # Replace with your password
        )
        
        if con.is_connected():
            cursor = con.cursor()
            
            # Execute the INSERT query
            query = "INSERT INTO STUDENTS (name, age) VALUES (%s, %s)"
            cursor.execute(query, (name, age))
            
            # Commit the transaction
            con.commit()
            
    except Error as err:
        raise err
    
    finally:
        # Close database connection
        if con.is_connected():
            cursor.close()
            con.close()