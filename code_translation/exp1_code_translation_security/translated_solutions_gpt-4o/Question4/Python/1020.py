import mysql.connector

def inserter(name, age):
    # Establish a connection to the database
    con = mysql.connector.connect(
        host="localhost",
        user="yourusername",
        password="yourpassword",
        database="mydb"
    )

    try:
        # Create a cursor object
        cursor = con.cursor()
        print("Connected!")

        # SQL query to insert data into the STUDENTS table
        sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
        values = (name, age)

        # Execute the query
        cursor.execute(sql, values)

        # Commit the transaction
        con.commit()

        print("1 record inserted")
    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        # Close the connection
        cursor.close()
        con.close()