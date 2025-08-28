import sqlite3
from sqlite3 import Error

# Define the database file name
DATABASE_FILE = "students_db.db"

def get_db_connection():
    """Creates and returns a database connection object."""
    conn = None
    try:
        conn = sqlite3.connect(DATABASE_FILE)
        return conn
    except Error as e:
        print(f"Error connecting to database: {e}")
    return conn

def create_table():
    """
    Python function to create a SQL table "students" with 'id', 'name', and 'age'.
    This is equivalent to the first JavaScript function.
    """
    # SQL statement to create the table
    sql_create_students_table = """
    CREATE TABLE IF NOT EXISTS students (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT NOT NULL,
        age INTEGER
    );
    """

    conn = get_db_connection()
    if conn is not None:
        try:
            # The 'with' statement handles transactions (commit/rollback) and closing the connection
            with conn:
                cursor = conn.cursor()
                cursor.execute(sql_create_students_table)
                print("Table 'students' created successfully or already exists.")
        except Error as e:
            # The alert in JS is replaced by a print statement for the console
            print(f"Error occurred while creating the table: {e}")
    else:
        print("Error! Cannot create the database connection.")


def insert_student(name, age):
    """
    Python function to insert a string name and an int age into the 'students' table.
    This is equivalent to the second JavaScript function.
    """
    sql_insert_student = "INSERT INTO students (name, age) VALUES (?, ?)"
    
    conn = get_db_connection()
    if conn is not None:
        try:
            with conn:
                cursor = conn.cursor()
                # The second argument to execute must be a tuple
                cursor.execute(sql_insert_student, (name, age))
                print(f"Successfully inserted student: {name}, Age: {age}")
        except Error as e:
            print(f"Error occurred while inserting student: {e}")
    else:
        print("Error! Cannot create the database connection.")


# --- Example Usage ---
if __name__ == "__main__":
    # 1. Create the table (it's safe to run this multiple times)
    create_table()

    # 2. Insert some students into the table
    print("\n--- Inserting new students ---")
    insert_student("Alice", 22)
    insert_student("Bob", 25)
    insert_student("Charlie", 20)