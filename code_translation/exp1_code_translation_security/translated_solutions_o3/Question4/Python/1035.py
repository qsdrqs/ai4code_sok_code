import sqlite3

# Open (or create) the database file
db = sqlite3.connect("studentdb")

# Ensure the STUDENTS table exists
db.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)")

def insert(name, age):
    """
    Insert a (name, age) pair into the STUDENTS table.
    """
    with db:  # commits automatically on success / rolls back on exception
        db.execute("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", (name, age))
        # Uncomment below to inspect all rows after each insert
        # for row in db.execute("SELECT * FROM STUDENTS"):
        #     print(row)

# Add two example rows
insert("Foo", 42)
insert("Bar", 55)

# Close the database connection
db.close()