import sqlite3

def add_new_entry(db, name, age):
    """Add a new entry to the STUDENTS table."""
    cursor = db.cursor()
    cursor.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT)")
    cursor.execute("INSERT INTO STUDENTS VALUES (?, ?)", (name, age))
    db.commit()

def main():
    # Connect to an in-memory database
    with sqlite3.connect(':memory:') as db:
        # Add new entries
        add_new_entry(db, "XXXXX", 0)
        add_new_entry(db, "XXXXX", 0)

        # Retrieve and print all entries
        cursor = db.cursor()
        cursor.execute("SELECT NAME, AGE FROM STUDENTS")
        rows = cursor.fetchall()
        for row in rows:
            print(dict(zip(["NAME", "AGE"], row)))

if __name__ == "__main__":
    main()