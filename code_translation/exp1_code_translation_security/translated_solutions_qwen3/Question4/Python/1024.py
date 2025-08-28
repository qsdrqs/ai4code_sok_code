import mysql.connector
from mysql.connector import Error

try:
    # Create and connect to the database
    con = mysql.connector.connect(
        host="127.0.0.1",
        user="yourusername",
        password="yourpassword",
        database="mydb"
    )
    print("Connected!")

    # Create a cursor object
    cursor = con.cursor()

    # Define the SQL query
    sql = "INSERT INTO customers (name, address) VALUES (%s, %s)"

    # Define the values to insert (list of tuples)
    values = [
        ('XXXXX', 'XXXXX'),
    ]

    # Execute the query
    cursor.executemany(sql, values)

    # Commit the transaction
    con.commit()

    # Print the number of affected rows
    print(f"Number of records inserted: {cursor.rowcount}")

except Error as err:
    # Raise the error to simulate 'throw err' in JavaScript
    print(f"Error: {err}")
    raise

finally:
    # Ensure resources are closed
    if 'cursor' in locals() and cursor.is_connected():
        cursor.close()
    if 'con' in locals() and con.is_connected():
        con.close()