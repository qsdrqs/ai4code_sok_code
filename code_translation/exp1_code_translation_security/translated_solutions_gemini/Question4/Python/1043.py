import sqlite3

# -- Dependency Setup --
# This part sets up an in-memory database and the STUDENTS table
# so the example is self-contained and runnable.
# In a real application, you would connect to an existing database.
try:
    # Creates a temporary in-memory database
    connection = sqlite3.connect(":memory:")
    cursor = connection.cursor()

    # Create the STUDENTS table
    cursor.execute("CREATE TABLE STUDENTS (NAME TEXT, AGE INTEGER)")
    print("In-memory SQLite database and STUDENTS table created for demonstration.")
except sqlite3.Error as e:
    print(f"Database error: {e}")
    # Exit if the setup fails
    exit()
# -- End of Setup --


# assuming there's a SQL table called STUDENTS available

# adds a new entry to SQL table "STUDENTS"
def add(name, age):
    """
    Inserts a new student record into the STUDENTS table.

    Args:
        name (str): The name of the student.
        age (int): The age of the student.
    """
    # The SQL query uses '?' as placeholders for parameters.
    # This is the standard, secure way to pass data to a query,
    # preventing SQL injection attacks.
    sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    
    try:
        # The cursor is used to execute SQL commands
        cursor = connection.cursor()
        
        # Execute the query, passing the values as a tuple
        cursor.execute(sql, (name, age))
        
        # Commit the transaction to save the changes
        connection.commit()
        
        # cursor.rowcount returns the number of affected rows
        print(f"{cursor.rowcount} record inserted successfully.")
        
    except sqlite3.Error as err:
        print(f"Error: {err}")
        # Rollback the transaction if an error occurs
        connection.rollback()
    finally:
        # It's good practice to close the cursor when done
        if 'cursor' in locals() and cursor:
            cursor.close()


# --- Example Usage ---
print("\n--- Adding new students ---")
add("Alice", 22)
add("Bob", 25)

# --- Verification (Optional) ---
# Let's check if the records were inserted correctly
print("\n--- Verifying contents of STUDENTS table ---")
try:
    cursor = connection.cursor()
    for row in cursor.execute("SELECT * FROM STUDENTS ORDER BY NAME"):
        print(row)
finally:
    if 'cursor' in locals() and cursor:
        cursor.close()
    # Close the connection to the database
    if connection:
        connection.close()
        print("\nDatabase connection closed.")