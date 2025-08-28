import mysql.connector

def add_student(name, age):
    # Establish a connection to the database
    connection = mysql.connector.connect(
        host="your_host",  # e.g., "localhost"
        user="your_username",
        password="your_password",
        database="your_database"
    )

    # Create a cursor object
    cursor = connection.cursor()

    # Define the SQL query with placeholders
    sql = "INSERT INTO STUDENTS (name, age) VALUES (%s, %s)"
    values = (name, age)

    try:
        # Execute the SQL query
        cursor.execute(sql, values)

        # Commit the transaction
        connection.commit()

        print("1 record inserted")
    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        # Close the cursor and connection
        cursor.close()
        connection.close()

# Example usage
# add_student("John Doe", 20)