import sqlite3
import os

# Global database connection
db_path = 'mydb.sqlite'

def create_table():
    """Python function to create SQL table "students" with 'name' and 'age'"""
    try:
        # Connect to database (creates if doesn't exist)
        conn = sqlite3.connect(db_path)
        cursor = conn.cursor()
        
        # Execute SQL to create table
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS students (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                age INTEGER
            )
        """)
        
        # Commit changes and close connection
        conn.commit()
        conn.close()
        
        print("Table created successfully")
        
    except sqlite3.Error as error:
        print("Error occurred while creating the table.")
        if conn:
            conn.close()

def insert_student(name, age):
    """Python function to insert string name and an int age to 'students'"""
    try:
        # Connect to database
        conn = sqlite3.connect(db_path)
        cursor = conn.cursor()
        
        # Execute SQL to insert data
        cursor.execute('INSERT INTO students (name, age) VALUES (?, ?)', (name, age))
        
        # Commit changes and close connection
        conn.commit()
        conn.close()
        
        print(f"Student {name} (age {age}) inserted successfully")
        
    except sqlite3.Error as error:
        print(f"Error occurred while inserting student: {error}")
        if conn:
            conn.close()

# Example usage:
if __name__ == "__main__":
    # Create the table
    create_table()
    
    # Insert some sample students
    insert_student("John Doe", 20)
    insert_student("Jane Smith", 22)