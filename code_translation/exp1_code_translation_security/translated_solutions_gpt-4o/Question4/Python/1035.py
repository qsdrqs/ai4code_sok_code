import sqlite3

# Connect to the database (or create it if it doesn't exist)
db = sqlite3.connect('studentdb')

# Create a cursor object to interact with the database
cursor = db.cursor()

# Create the STUDENTS table if it doesn't exist
cursor.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)")

def insert(name, age):
    # Insert a new student record into the STUDENTS table
    cursor.execute("INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)", (name, age))
    db.commit()

    # Uncomment the following lines to print all records in the STUDENTS table
    # cursor.execute("SELECT * FROM STUDENTS")
    # for row in cursor.fetchall():
    #     print(row)

# Insert sample data
insert("Foo", 42)
insert("Bar", 55)

# Close the database connection
db.close()