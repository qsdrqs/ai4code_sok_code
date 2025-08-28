import sqlite3


def add_new_entry(name, age):
    # Connect to (or create) the SQLite database
    conn = sqlite3.connect('database.db')

    # So we can access columns by name (row['NAME'], etc.)
    conn.row_factory = sqlite3.Row
    cur = conn.cursor()

    # Insert the new student
    cur.execute("INSERT INTO STUDENTS VALUES (?, ?)", (name, age))
    conn.commit()

    # Display all current rows
    for row in cur.execute("SELECT rowid AS id, NAME, AGE FROM STUDENTS"):
        print(f"{row['id']}: {row['NAME']} {row['AGE']}")

    # Clean-up
    conn.close()


# Example call
add_new_entry("XXXXX", 0)