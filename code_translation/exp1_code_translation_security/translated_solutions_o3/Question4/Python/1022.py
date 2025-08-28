import sqlite3

# Open (or create) the SQLite database
DB_PATH = '/home/codex/Documents/User_Study_UI/database.db'
conn = sqlite3.connect(DB_PATH)
cur = conn.cursor()


def student_write(name: str, age: int) -> None:
    """
    Insert a row into the STUDENTS table.
    """
    cur.execute("INSERT INTO STUDENTS (NAME, AGE) VALUES(?, ?)", (name, age))
    conn.commit()               # Persist the change


# Example insert
student_write("XXXXX", 0)

# Fetch and display all rows
cur.execute("SELECT * FROM STUDENTS")
rows = cur.fetchall()

for row in rows:
    print(row)                  # row is a tuple (NAME, AGE, …)

# Close the connection when finished
conn.close()