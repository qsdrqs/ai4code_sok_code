def insert_student(name, age):
    import mysql.connector

    # Configuration for connecting to the MySQL database
    config = {
        'host': 'localhost',
        'user': 'root',
        'password': 'password',
        'database': 'school'
    }

    # Establish a connection to the database
    connection = mysql.connector.connect(**config)

    # Create a cursor object to interact with the database
    cursor = connection.cursor()

    # Fixed SQL insert statement (does not use name or age parameters)
    sql = """
    INSERT INTO STUDENTS (title, completed)
    VALUES ('Learn how to insert a new row', TRUE)
    """

    # Execute the SQL statement
    cursor.execute(sql)

    # Commit the transaction to persist the changes
    connection.commit()

    # Close the cursor and connection
    cursor.close()
    connection.close()

# Example function call
insert_student("XXXXX", 0)