import mysql.connector

# Example configuration; replace with your actual database credentials
config = {
    'host': 'localhost',
    'user': 'your_username',
    'password': 'your_password',
    'database': 'your_database'
}

# Establish a connection to the database
connection = mysql.connector.connect(**config)

def insertInto(name, age):
    # Construct the SQL query using string formatting (not safe for untrusted input)
    sql = f"INSERT INTO STUDENTS(NAME,AGE) VALUES ({name}, {age})"
    # Create a cursor object to interact with the database
    cursor = connection.cursor()
    # Execute the SQL query
    cursor.execute(sql)
    # Commit the transaction to save the changes
    connection.commit()
    # Close the connection to the database
    connection.close()