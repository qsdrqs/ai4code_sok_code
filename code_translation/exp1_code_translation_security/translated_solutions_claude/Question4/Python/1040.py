import sqlite3

# Create an in-memory database
db = sqlite3.connect(':memory:')

def addNewEntry(name, age):
    cursor = db.cursor()
    
    # Create table if it doesn't exist
    cursor.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT)")
    
    # Insert data using parameterized query
    cursor.execute("INSERT INTO STUDENTS VALUES (?, ?)", (name, age))
    
    # Commit the transaction
    db.commit()
    
    # Close the cursor
    cursor.close()

# Add entries
addNewEntry("XXXXX", 0)
addNewEntry("XXXXX", 0)

# Query and print results
cursor = db.cursor()
cursor.execute("SELECT NAME, AGE FROM STUDENTS")
rows = cursor.fetchall()

for row in rows:
    # Convert to dictionary format similar to JavaScript row object
    row_dict = {"NAME": row[0], "AGE": row[1]}
    print(row_dict)

cursor.close()
db.close()