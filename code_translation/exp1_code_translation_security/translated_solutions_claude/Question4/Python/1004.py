import mysql.connector

def add_student(name, age):
    """
    Python function add_student
    
    Given a String name and int age, add entry to table STUDENTS
    """
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    print(sql)
    return sql

# Create connection
try:
    con = mysql.connector.connect(
        host="localhost",
        user="yourusername",
        password="yourpassword"
    )
    print("Connected!")
    
    # Create cursor and execute query
    cursor = con.cursor()
    cursor.execute(add_student("XXXXX", "0"))
    
    # Commit the transaction
    con.commit()
    
    print(f"Result: {cursor.rowcount} row(s) affected")
    
except mysql.connector.Error as err:
    print(f"Error: {err}")
    
finally:
    # Close cursor and connection
    if 'cursor' in locals():
        cursor.close()
    if 'con' in locals() and con.is_connected():
        con.close()