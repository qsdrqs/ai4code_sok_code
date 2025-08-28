import sqlite3

def add_new_entry(name, age):
    # Connect to the SQLite database
    db = sqlite3.connect('database.db')
    
    # Create a cursor object to execute SQL commands
    cursor = db.cursor()
    
    # Insert a new entry into the STUDENTS table
    cursor.execute("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", (name, age))
    
    # Commit the changes
    db.commit()
    
    # Retrieve and print all entries from the STUDENTS table
    cursor.execute("SELECT rowid, NAME, AGE FROM STUDENTS")
    for row in cursor.fetchall():
        print(f"{row[0]}: {row[1]} {row[2]}")
    
    # Close the database connection
    db.close()

# Call the function with example data
add_new_entry("XXXXX", 0)