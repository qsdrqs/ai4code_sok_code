import mysql.connector

def insertion(name, age):
    try:
        # Create connection (equivalent to mysql.createConnection())
        con = mysql.connector.connect(
            host="localhost",
            user="yourusername",
            password="yourpassword",
            database="yourdatabase"
        )
    except mysql.connector.Error as err:
        # Equivalent to: if (err) throw err;
        raise err
    
    cursor = con.cursor()
    
    # Parameterized query to prevent SQL injection
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %s)"
    values = (name, age)
    
    cursor.execute(sql, values)
    con.commit()  # Commit transaction to persist changes
    
    cursor.close()
    con.close()