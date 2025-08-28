import sqlite3

def add_student(name, age):
    command = f"INSERT INTO STUDENTS VALUES ('{name}', '{age}');"
    
    # Create connection to database
    connection = sqlite3.connect('database.db')  # Replace with your database file
    cursor = connection.cursor()
    
    # Execute the command
    cursor.execute(command)
    
    # Commit the changes and close connection
    connection.commit()
    connection.close()