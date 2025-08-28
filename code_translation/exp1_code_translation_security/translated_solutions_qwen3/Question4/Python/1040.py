import sqlite3

# Connect to an in-memory SQLite database
conn = sqlite3.connect(':memory:')
cursor = conn.cursor()

def add_new_entry(name, age):
    """
    Adds a new entry to the STUDENTS table.
    Creates the table if it doesn't already exist.
    """
    # Create the table if it doesn't exist
    cursor.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INTEGER)")

    # Insert a new row using parameterized query
    cursor.execute("INSERT INTO STUDENTS VALUES (?, ?)", (name, age))

    # Commit the transaction to persist the changes
    conn.commit()

# Add two entries to the database
add_new_entry("XXXXX", 0)
add_new_entry("XXXXX", 0)

# Query the database and print each row
cursor.execute("SELECT NAME, AGE FROM STUDENTS")
for row in cursor.fetchall():
    print(row)