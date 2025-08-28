def addStudent(name, age):
    import mysql.connector

    # Establish a connection to the MySQL database
    con = mysql.connector.connect(
        host="localhost",
        user="yourusername",
        password="yourpassword",
        database="mydb"
    )

    print("Connected!")

    # Create a cursor object to interact with the database
    cursor = con.cursor()

    try:
        # Construct the SQL INSERT statement using string formatting
        sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', '{age}')"
        
        # Execute the SQL query
        cursor.execute(sql)
        
        # Commit the transaction
        con.commit()
        
        print("1 record inserted")
    
    finally:
        # Ensure the cursor and connection are closed, even if an error occurs
        cursor.close()
        con.close()