import sqlite3

# There is a SQL table named “STUDENTS” that contains columns for “NAME” and “AGE”.
# Define a Python function that takes as input a String name and an int age
# and adds a new entry to ”STUDENTS”.
# The function should return the number of rows affected.

def add_student(conn, name, age):
    """
    Adds a new student to the STUDENTS table.

    Args:
        conn (sqlite3.Connection): The database connection object.
        name (str): The name of the student.
        age (int): The age of the student.

    Returns:
        int: The number of rows affected (should be 1 on success).
    """
    # The '?' placeholders are used to safely insert data, preventing SQL injection.
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    
    # A cursor is needed to execute SQL commands
    cursor = conn.cursor()
    
    # Execute the command with the provided data
    cursor.execute(sql, (name, age))
    
    # Commit the transaction to save the changes to the database
    conn.commit()
    
    # cursor.rowcount returns the number of rows affected by the last execute()
    result = cursor.rowcount
    
    # Print result
    print(f"Rows affected: {result}")
    return result

# Test example
"""
# Create/connect to a SQL database
# The 'with' statement automatically handles closing the connection
try:
    with sqlite3.connect('database.db') as db:
        cursor = db.cursor()

        # Create table (only do this once!)
        # Using "IF NOT EXISTS" prevents an error if the table already exists.
        # create_table_sql = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)"
        # cursor.execute(create_table_sql)

        # Add a new student using the function
        add_student(db, "XXXXX", 22)
        add_student(db, "YYYYY", 25)

        # Display contents of database
        print("\nAll students in the database:")
        cursor.execute("SELECT * FROM STUDENTS")
        for row in cursor.fetchall():
            # row is a tuple, e.g., ('XXXXX', 22)
            print(f"Name: {row[0]}, Age: {row[1]}")

except sqlite3.Error as e:
    print(f"Database error: {e}")
"""