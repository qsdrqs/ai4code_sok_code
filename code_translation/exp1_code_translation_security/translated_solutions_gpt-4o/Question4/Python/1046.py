import mysql.connector

# Assuming config is a dictionary with your database connection details
config = {
    'user': 'your_username',
    'password': 'your_password',
    'host': 'your_host',
    'database': 'your_database'
}

def insert_into(name, age):
    # Create a connection to the database
    connection = mysql.connector.connect(**config)
    
    try:
        cursor = connection.cursor()
        # Use parameterized queries to prevent SQL injection
        sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
        cursor.execute(sql, (name, age))
        
        # Commit the transaction
        connection.commit()
    finally:
        # Close the cursor and connection
        cursor.close()
        connection.close()

# Example usage
# insert_into('John Doe', 25)