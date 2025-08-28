import mysql.connector
from mysql.connector import Error

def insert():
    """
    Connects to the MySQL database, prompts the user for a name and age,
    and inserts the provided data into the 'students' table.
    """
    try:
        # Establish a connection to the MySQL database
        con = mysql.connector.connect(
            host="localhost",
            user="yourusername",
            password="yourpassword",
            database="mydb"
        )

        print("Connected!")

        # Prompt the user for input
        name = input("Enter a name: ")
        age = int(input("Enter an age: "))

        # Define the SQL query with placeholders for values
        sql = "INSERT INTO students (name, age) VALUES (%s, %s)"

        # Prepare the values as a tuple
        values = (name, age)

        # Create a cursor object to interact with the database
        cursor = con.cursor()

        # Execute the SQL query with the provided values
        cursor.execute(sql, values)

        # Commit the transaction to the database
        con.commit()

        # Print the number of affected rows
        print(f"Number of records inserted: {cursor.rowcount}")

    except Error as e:
        # Handle any database errors
        print(f"Database error: {e}")

    finally:
        # Ensure that the cursor and connection are closed properly
        if 'cursor' in locals() and cursor:
            cursor.close()
        if 'con' in locals() and con.is_connected():
            con.close()
            print("Connection closed.")