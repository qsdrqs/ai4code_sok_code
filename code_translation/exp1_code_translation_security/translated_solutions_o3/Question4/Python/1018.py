def add(name, age, table):
    # All the required dependencies (`mysql.connector` or a drop-in replacement
    # and a `config.py` file containing the connection parameters) are assumed
    # to be available.
    
    import mysql.connector          # or the library specified in your deps
    import config                   # the Python version of `config.js`

    try:
        # Establish a connection
        cnx = mysql.connector.connect(**config.DB_CONFIG)   # or simply **config if that’s how it’s exported
        cursor = cnx.cursor()

        # Data to be inserted
        todos = (name, age)

        # Execute the INSERT
        stmt = "INSERT INTO todos (title, completed) VALUES (%s, %s)"
        cursor.execute(stmt, todos)

        # Make sure the change is committed
        cnx.commit()

        # `rowcount` tells us how many rows were affected
        print(f"Row inserted: {cursor.rowcount}")

    except mysql.connector.Error as err:
        print(f"Error: {err}")

    finally:
        # Clean-up: close cursor and connection whether or not the operation succeeded
        if cursor:
            cursor.close()
        if cnx:
            cnx.close()