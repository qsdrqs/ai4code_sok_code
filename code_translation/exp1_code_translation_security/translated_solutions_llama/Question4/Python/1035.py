import sqlite3

def create_table(db):
    """Create STUDENTS table if it does not exist."""
    cursor = db.cursor()
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS STUDENTS (
            NAME TEXT,
            AGE INT
        )
    """)
    db.commit()

def insert_student(db, name, age):
    """Insert a student into the STUDENTS table."""
    cursor = db.cursor()
    cursor.execute("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", (name, age))
    db.commit()

def main():
    # Establish a connection to the database
    db = sqlite3.connect('studentdb')

    # Create the STUDENTS table
    create_table(db)

    # Insert students
    insert_student(db, "Foo", 42)
    insert_student(db, "Bar", 55)

    # Fetch and print all students (equivalent to db.each in JavaScript)
    cursor = db.cursor()
    cursor.execute("SELECT * FROM STUDENTS")
    rows = cursor.fetchall()
    for row in rows:
        print(row)

    # Close the database connection
    db.close()

if __name__ == "__main__":
    main()