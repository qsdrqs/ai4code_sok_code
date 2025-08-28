def add(name, age, table):
    import mysql.connector
    import config

    # Establish a database connection using the configuration
    connection = mysql.connector.connect(**config.config)
    cursor = connection.cursor()

    try:
        # Define the SQL INSERT statement
        stmt = "INSERT INTO todos (title, completed) VALUES (%s, %s)"

        # Execute the query with the provided name and age
        cursor.execute(stmt, (name, age))

        # Commit the transaction
        connection.commit()

        # Print the number of rows affected
        print(f"Row inserted: {cursor.rowcount}")

    except Exception as err:
        # Print the error message if an exception occurs
        print(f"Error message: {err}")

        # Optionally rollback the transaction on error
        connection.rollback()

    finally:
        # Always close the cursor and connection
        cursor.close()
        connection.close()