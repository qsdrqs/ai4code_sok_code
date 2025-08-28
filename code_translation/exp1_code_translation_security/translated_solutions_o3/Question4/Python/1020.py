import mysql.connector   # dependency: `mysql-connector-python`
from mysql.connector import Error


def inserter(name, age):
    try:
        # Open a connection
        con = mysql.connector.connect(
            host="localhost",
            user="yourusername",
            password="yourpassword",
            database="mydb"
        )

        if con.is_connected():
            print("Connected!")

            cursor = con.cursor()

            # Use placeholders to avoid SQL-injection issues
            sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
            cursor.execute(sql, (name, age))

            # Commit the transaction
            con.commit()
            print("1 record inserted")

    except Error as err:
        # Re-raise so callers can still handle it, or simply print/log
        raise err

    finally:
        # Clean-up
        if con.is_connected():
            cursor.close()
            con.close()