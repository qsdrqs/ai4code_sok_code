# Note: Ensure you have the `mysql-connector-python` package installed.
import mysql.connector

# Create a connection to the database
con = mysql.connector.connect(
    host="127.0.0.1",
    user="yourusername",
    password="yourpassword",
    database="mydb"
)

# Connect to your database
try:
    if con.is_connected():
        print("Connected!")
        cursor = con.cursor()

        # SQL query to insert data
        sql = "INSERT INTO customers (name, address) VALUES (%s, %s)"
        values = [
            ('XXXXX', 'XXXXX'),
        ]

        # Execute the query
        cursor.executemany(sql, values)
        con.commit()

        print("Number of records inserted:", cursor.rowcount)

except mysql.connector.Error as err:
    print("Error:", err)

finally:
    # Close the connection
    if con.is_connected():
        cursor.close()
        con.close()
        print("Connection closed.")