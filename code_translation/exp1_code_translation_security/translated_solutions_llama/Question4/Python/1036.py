import sqlite3

def add_new_entry(name, age):
    """
    Adds a new entry to the STUDENTS table in the database and prints all entries.

    Args:
        name (str): The name of the student.
        age (int): The age of the student.
    """

    # Connect to the SQLite database
    conn = sqlite3.connect('database.db')
    cursor = conn.cursor()

    try:
        # Create table if it doesn't exist (optional)
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS STUDENTS
            (NAME TEXT, AGE INTEGER)
        ''')

        # Insert a new entry into the STUDENTS table
        cursor.execute("INSERT INTO STUDENTS VALUES (?, ?)", (name, age))

        # Commit the transaction
        conn.commit()

        # Fetch and print all entries from the STUDENTS table
        cursor.execute("SELECT rowid AS id, NAME, AGE FROM STUDENTS")
        rows = cursor.fetchall()
        for row in rows:
            print(f"{row[0]}: {row[1]} {row[2]}")

    except sqlite3.Error as e:
        print(f"An error occurred: {e}")

    finally:
        # Close the connection
        conn.close()

# Example usage
add_new_entry("XXXXX", 0)