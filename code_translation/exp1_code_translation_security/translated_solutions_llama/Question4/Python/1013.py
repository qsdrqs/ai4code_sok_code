import mysql.connector
from getpass import getpass
from typing import List, Tuple

# Database connection settings
config = {
    'host': 'localhost',
    'user': 'yourusername',
    'password': '',
    'database': 'mydb'
}

def insert_record():
    # Get user input for name and age
    name = input("Enter a name: ")
    age = int(input("Enter an age: "))

    try:
        # Establish a connection to the database
        cnx = mysql.connector.connect(**config)
        cursor = cnx.cursor()

        # SQL query to insert a record
        sql = "INSERT INTO students (name, age) VALUES (%s, %s)"
        values: List[Tuple[str, int]] = [(name, age)]

        # Execute the query
        cursor.executemany(sql, values)

        # Commit the changes
        cnx.commit()

        # Print the number of records inserted
        print(f"Number of records inserted: {cursor.rowcount}")

    except mysql.connector.Error as err:
        print(f"Error: {err}")

    finally:
        # Close the cursor and connection
        if 'cursor' in locals():
            cursor.close()
        if 'cnx' in locals():
            cnx.close()

if __name__ == "__main__":
    # Get the password from the user (optional, but recommended for security)
    config['password'] = getpass("Enter your password: ")
    insert_record()