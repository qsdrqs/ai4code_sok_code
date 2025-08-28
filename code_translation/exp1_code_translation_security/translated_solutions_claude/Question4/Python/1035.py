import sqlite3

# Connect to database (creates file if it doesn't exist)
db = sqlite3.connect('studentdb')

# Create cursor object to execute SQL commands
cursor = db.cursor()

# Create table if it doesn't exist
cursor.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)")

def insert(name, age):
    # Prepare and execute the INSERT statement
    stmt = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)"
    cursor.execute(stmt, (name, age))
    
    # Commit the transaction
    db.commit()
    
    # Commented out section equivalent:
    # cursor.execute("SELECT * FROM STUDENTS")
    # rows = cursor.fetchall()
    # for row in rows:
    #     print(row)

# Insert data
insert("Foo", 42)
insert("Bar", 55)

# Close the database connection
db.close()