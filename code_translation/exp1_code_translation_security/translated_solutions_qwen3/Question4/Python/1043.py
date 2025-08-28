import mysql.connector

# Establish a connection to the database
# Replace the placeholders with your actual database credentials
connection = mysql.connector.connect(
    host='localhost',
    user='your_username',
    password='your_password',
    database='your_database'
)

# Create a cursor object to interact with the database
cursor = connection.cursor()

def add(name, age):
    """
    Adds a new entry to the SQL table "STUDENTS".
    
    Parameters:
    name (str): The name of the student.
    age (int): The age of the student.
    """
    # Construct the SQL query using string formatting
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    
    # Log the SQL query (equivalent to console.log in JavaScript)
    print(sql)
    
    try:
        # Execute the SQL query
        cursor.execute(sql)
        
        # Commit the transaction to the database
        connection.commit()
        
        # Log success message
        print("1 record inserted")
    
    except Exception as err:
        # Raise the error to match the behavior of `throw err` in JavaScript
        raise err