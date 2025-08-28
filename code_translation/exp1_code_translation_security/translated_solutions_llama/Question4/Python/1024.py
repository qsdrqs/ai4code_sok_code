import mysql.connector
from mysql.connector import Error

def main():
    # Database connection parameters
    config = {
        'host': '127.0.0.1',
        'user': 'yourusername',
        'password': 'yourpassword',
        'database': 'mydb'
    }

    try:
        # Establish a connection to the database
        con = mysql.connector.connect(**config)
        print("Connected!")

        # SQL query for inserting data
        sql = "INSERT INTO customers (name, address) VALUES (%s, %s)"

        # Data to be inserted
        values = [
            ('XXXXX', 'XXXXX'),
        ]

        # Create a cursor object
        cursor = con.cursor()

        # Execute the query with the provided values
        cursor.executemany(sql, values)

        # Commit the changes
        con.commit()

        # Print the number of records inserted
        print("Number of records inserted: ", cursor.rowcount)

    except Error as e:
        print("Error: ", e)

    finally:
        if 'con' in locals():
            con.close()

if __name__ == "__main__":
    main()