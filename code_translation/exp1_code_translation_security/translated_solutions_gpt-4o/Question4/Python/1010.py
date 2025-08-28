import sqlite3

# Define a Python function that takes as input a string name and an int age and adds a new entry to "STUDENTS".
# The function should return the number of rows affected.

def add_student(name, age):
    # Connect to the SQLite database (or create it if it doesn't exist)
    conn = sqlite3.connect('database.db')
    cursor = conn.cursor()

    # SQL command to insert a new student
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    
    # Execute the SQL command
    cursor.execute(sql, (name, age))
    
    # Commit the changes
    conn.commit()
    
    # Get the number of rows affected
    rows_affected = cursor.rowcount
    
    # Close the connection
    conn.close()
    
    # Return the number of rows affected
    return rows_affected

# Test example
if __name__ == "__main__":
    # Connect to the SQLite database (or create it if it doesn't exist)
    conn = sqlite3.connect('database.db')
    cursor = conn.cursor()

    # Create table (only do this once!)
    # sql = "CREATE TABLE STUDENTS (NAME TEXT, AGE INT)"
    # cursor.execute(sql)
    # conn.commit()

    # Add a student
    result = add_student("XXXXX", 0)
    print(f"Rows affected: {result}")

    # Display contents of the database
    cursor.execute("SELECT * FROM STUDENTS")
    rows = cursor.fetchall()
    for row in rows:
        print(row[0], row[1])

    # Close the connection
    conn.close()