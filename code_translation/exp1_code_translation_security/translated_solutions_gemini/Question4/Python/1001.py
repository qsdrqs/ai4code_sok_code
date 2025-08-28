import sqlite3

def add_sql_entry(name, age):
    """
    Inserts a new student record into the database.

    Args:
        name (str): The name of the student.
        age (int): The age of the student.
    """
    # The database connection. Using a 'with' statement ensures the connection is
    # automatically closed even if errors occur.
    # 'students.db' will be created if it doesn't exist.
    conn = None
    try:
        with sqlite3.connect('students.db') as conn:
            cursor = conn.cursor()
            
            # The SQL query uses '?' as a placeholder to prevent SQL injection.
            sql = "INSERT INTO students (name, age) VALUES (?, ?)"
            params = (name, age) # Parameters must be a tuple
            
            cursor.execute(sql, params)
            
            # The 'with' block automatically commits the transaction on success.
            
            # cursor.lastrowid is the equivalent of 'this.lastID'
            print(f"User added with id: {cursor.lastrowid}")

    except sqlite3.Error as e:
        # The equivalent of the 'if (err)' block
        print(f"Database error: {e}")

# --- Helper function to set up the database for a runnable example ---
def setup_database():
    """Creates the students table if it doesn't exist."""
    with sqlite3.connect('students.db') as conn:
        cursor = conn.cursor()
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS students (
                id INTEGER PRIMARY KEY,
                name TEXT NOT NULL,
                age INTEGER NOT NULL
            )
        """)
        print("Database and table are ready.")

# --- Example Usage ---
if __name__ == "__main__":
    setup_database()
    print("-" * 20)
    add_sql_entry("Alice", 22)
    add_sql_entry("Bob", 25)