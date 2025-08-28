import mysql.connector
from mysql.connector import Error

# Assuming the config is defined elsewhere
config = {
    'host': 'your_host',
    'database': 'your_database',
    'user': 'your_user',
    'password': 'your_password'
}

def insert_student(name, age):
    try:
        # Establish a connection to the database
        connection = mysql.connector.connect(**config)
        
        # Create a cursor object
        cursor = connection.cursor()
        
        # Insert statement (Note: The original JavaScript code was inserting hardcoded values.
        # This example uses parameterized queries for better security.)
        sql = "INSERT INTO STUDENTS (title, completed) VALUES (%s, %s)"
        values = ('Learn how to insert a new row', True)
        
        # Execute the insert statement
        cursor.execute(sql, values)
        
        # Commit the changes
        connection.commit()
        
        print("Record inserted successfully.")
    
    except Error as e:
        print(f"Error: '{e}'")
    
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()
            print("MySQL connection is closed")

# Example usage
insert_student("XXXXX", 0)