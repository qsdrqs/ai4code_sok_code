import sqlite3

def add_new_entry(name, age):
    # Connect to the database
    db = sqlite3.connect('database.db')
    
    # Create a cursor object
    cursor = db.cursor()
    
    try:
        # Insert new entry
        cursor.execute("INSERT INTO STUDENTS VALUES (?,?)", (name, age))
        
        # Commit the transaction
        db.commit()
        
        # Select and display all entries
        cursor.execute("SELECT rowid AS id, NAME, AGE FROM STUDENTS")
        rows = cursor.fetchall()
        
        for row in rows:
            print(f"{row[0]}: {row[1]} {row[2]}")
            
    except sqlite3.Error as e:
        print(f"Database error: {e}")
        db.rollback()
    
    finally:
        # Close the database connection
        db.close()

# Call the function
add_new_entry("XXXXX", 0)