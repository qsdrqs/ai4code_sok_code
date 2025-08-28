import sqlite3
import os
import sys

# The original JavaScript code demonstrates handling asynchronous operations with callbacks.
# The Python equivalent is synchronous, so the logic can be executed sequentially
# without nested callbacks.

def create_tables_and_insert(conn):
    """
    Creates the 'students' table and then inserts a row.
    This function combines the logic of JavaScript's createTables2 and insert.
    """
    print("pre-created tables")
    try:
        cursor = conn.cursor()
        # In Python, the next line of code will only run after this one completes.
        cursor.execute('CREATE TABLE students(name TEXT, age INT)')
        
        # The insert logic can now be called directly.
        insert(conn, 'John Doe', 30)

    except sqlite3.Error as e:
        print(f"Error during table creation or insertion: {e}")


def insert(conn, name, age):
    """
    Inserts a new row into the students table.
    """
    sql = "INSERT INTO students(name, age) VALUES (?, ?)"
    try:
        cursor = conn.cursor()
        # Parameters are passed as a tuple.
        cursor.execute(sql, (name, age))
        print("row added successfully")
    except sqlite3.Error as e:
        # The original JS code checked for err.message, here we just print the error.
        print(f"Error inserting row: {e}")


def create_database():
    """
    Main function to connect to the database, create tables, and insert data.
    This replaces the logic in the original JS createDatabase function and the IIFE.
    """
    db_file = 'temp.db'
    
    # For a clean run, remove the old db file if it exists
    if os.path.exists(db_file):
        os.remove(db_file)
        
    print("created database")
    conn = None  # Initialize conn to None so it can be used in the finally block
    try:
        # JS: new sqlite3.Database('temp.db', (err) => { ... });
        # Python's connect is synchronous and will raise an exception on error.
        conn = sqlite3.connect(db_file)
        
        # The logic from the JS callback now runs sequentially.
        # JS: createTables2(newdb);
        create_tables_and_insert(conn)
        
        # IMPORTANT: In Python, you must explicitly commit transactions.
        conn.commit()

    except sqlite3.Error as e:
        # JS: if (err) { console.log("Getting error " + err); exit(1); }
        print(f"Getting error {e}")
        sys.exit(1)
    finally:
        # Always ensure the connection is closed.
        if conn:
            conn.close()

# This is the standard entry point for a Python script,
# equivalent to the IIFE in the JavaScript code.
if __name__ == "__main__":
    create_database()

    # The following block is equivalent to the commented-out `setTimeout`
    # in the original JS, demonstrating how to query the data.
    print("\n--- Verifying Data ---")
    try:
        conn = sqlite3.connect('temp.db')
        cursor = conn.cursor()
        cursor.execute("SELECT * FROM students")
        
        rows = cursor.fetchall() # Fetches all resulting rows
        for row in rows:
            # row is a tuple, e.g., ('John Doe', 30)
            print(f"Name: {row[0]}, Age: {row[1]}")
            
    except sqlite3.Error as e:
        print(f"Error querying data: {e}")
    finally:
        if 'conn' in locals() and conn:
            conn.close()