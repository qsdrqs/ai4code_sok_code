import sqlite3

# Create a database connection
conn = sqlite3.connect('database.db')
cur = conn.cursor()

def add_student(name, age):
    """
    Adds a new student to the STUDENTS table and returns the number of rows affected.
    
    Parameters:
        name (str): The name of the student.
        age (int): The age of the student.
    
    Returns:
        int: Number of rows affected by the insert operation.
    """
    # Construct the SQL query using string formatting (as in the original JS code)
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    
    # Execute the SQL query
    cur.execute(sql)
    
    # Retrieve the number of rows affected using SQLite's changes() function
    cur.execute("SELECT changes()")
    affected_rows = cur.fetchone()[0]
    
    # Commit the transaction
    conn.commit()
    
    return affected_rows

# Test example (commented out for safety)
if __name__ == "__main__":
    # Uncomment the following line to create the table (only once)
    # cur.execute("CREATE TABLE STUDENTS (NAME TEXT, AGE INT)")
    
    # Add a new student
    rows_affected = add_student("XXXXX", 0)
    print(f"Rows affected: {rows_affected}")
    
    # Display contents of the database
    cur.execute("SELECT * FROM STUDENTS")
    rows = cur.fetchall()
    for row in rows:
        print(row[0], row[1])
    
    # Close the database connection
    conn.close()