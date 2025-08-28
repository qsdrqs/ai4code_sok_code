import sqlite3

# Create an in-memory SQLite database
db = sqlite3.connect(':memory:')

def add_new_entry(name, age):
    # Create a cursor object using the cursor() method
    cursor = db.cursor()
    
    # Create table if it doesn't exist
    cursor.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT)")
    
    # Insert a new entry into the STUDENTS table
    cursor.execute("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", (name, age))
    
    # Commit the transaction
    db.commit()

# Add new entries
add_new_entry("XXXXX", 0)
add_new_entry("XXXXX", 0)

# Query the database and print each row
for row in db.execute("SELECT NAME, AGE FROM STUDENTS"):
    print(row)

# Close the database connection
db.close()