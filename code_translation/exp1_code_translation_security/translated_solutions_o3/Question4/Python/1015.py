def add_student(name, age):
    import mysql.connector  # dependency will be supplied

    try:
        # Establish the connection
        conn = mysql.connector.connect(
            host="localhost",
            user="yourusername",
            password="yourpassword",
            database="mydb"
        )

        print("Connected!")
        cursor = conn.cursor()

        # Use a parameterised query to avoid SQL-injection issues
        sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
        cursor.execute(sql, (name, age))

        # Commit the transaction
        conn.commit()
        print("1 record inserted")

    except mysql.connector.Error as err:
        # Re-raise or handle the error as desired
        raise err

    finally:
        # Clean up
        if 'cursor' in locals():
            cursor.close()
        if 'conn' in locals() and conn.is_connected():
            conn.close()