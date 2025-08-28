import mysql.connector

# Assuming there's a SQL table called STUDENTS available

# Adds a new entry to SQL table "STUDENTS"
def add(name, age):
    # Establish a connection to the database
    connection = mysql.connector.connect(
        host='your_host',  # e.g., 'localhost'
        user='your_username',
        password='your_password',
        database='your_database'
    )

    cursor = connection.cursor()

    # Prepare the SQL query
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
    values = (name, age)

    try:
        # Execute the SQL command
        cursor.execute(sql, values)
        
        # Commit the changes to the database
        connection.commit()
        
        print("1 record inserted")
    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        # Close the cursor and connection
        cursor.close()
        connection.close()

# Example usage
# add('John Doe', 25)