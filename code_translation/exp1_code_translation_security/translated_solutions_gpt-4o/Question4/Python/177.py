import pyodbc

def add_student(name, age):
    # Define the connection string
    connection_string = 'DRIVER={SQL Server};SERVER=your_server;DATABASE=your_database;UID=your_username;PWD=your_password'
    
    # Create a connection to the database
    connection = pyodbc.connect(connection_string)
    
    # Create a cursor from the connection
    cursor = connection.cursor()
    
    # Define the SQL command
    command = f"INSERT INTO STUDENTS VALUES ('{name}', '{age}');"
    
    # Execute the SQL command
    cursor.execute(command)
    
    # Commit the transaction
    connection.commit()
    
    # Close the connection
    connection.close()

# Example usage
# add_student('John Doe', 20)