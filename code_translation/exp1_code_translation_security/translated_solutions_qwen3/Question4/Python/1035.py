import sqlite3

# Connect to the SQLite database (or create it if it doesn't exist)
conn = sqlite3.connect('studentdb')

# Create a cursor object to interact with the database
cursor = conn.cursor()

# Create the STUDENTS table if it doesn't already exist
cursor.execute('''
    CREATE TABLE IF NOT EXISTS STUDENTS (
        NAME TEXT,
        AGE INTEGER
    )
''')

# Define the insert function to add a new student
def insert(name, age):
    # Prepare and execute the INSERT statement
    cursor.execute('''
        INSERT INTO STUDENTS (NAME, AGE)
        VALUES (?, ?)
    ''', (name, age))
    
    # Commit the transaction to save the changes
    conn.commit()

# Insert two sample records into the STUDENTS table
insert("Foo", 42)
insert("Bar", 55)

# Optional: Uncomment to view all records in the STUDENTS table
# cursor.execute("SELECT * FROM STUDENTS")
# for row in cursor.fetchall():
#     print(row)

# Close the database connection
conn.close()