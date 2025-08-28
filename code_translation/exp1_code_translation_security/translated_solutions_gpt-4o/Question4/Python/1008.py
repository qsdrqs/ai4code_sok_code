import sqlite3

# Function to create SQL table "students" with 'name' and 'age'
def create_table():
    # Connect to the SQLite database (or create it if it doesn't exist)
    conn = sqlite3.connect('mydb.sqlite')
    cursor = conn.cursor()
    
    # Create table if it doesn't exist
    try:
        cursor.execute('''
            CREATE TABLE IF NOT EXISTS students (
                id INTEGER PRIMARY KEY,
                name TEXT,
                age INTEGER
            )
        ''')
        print("Table created successfully")
    except sqlite3.Error as error:
        print("Error occurred while creating the table:", error)
    
    # Commit the changes and close the connection
    conn.commit()
    conn.close()

# Function to insert string name and an int age to 'students'
def insert_student(name, age):
    # Connect to the SQLite database
    conn = sqlite3.connect('mydb.sqlite')
    cursor = conn.cursor()
    
    # Insert a new student record
    try:
        cursor.execute('INSERT INTO students (name, age) VALUES (?, ?)', (name, age))
        print("Student inserted successfully")
    except sqlite3.Error as error:
        print("Error occurred while inserting the student:", error)
    
    # Commit the changes and close the connection
    conn.commit()
    conn.close()

# Example usage
create_table()
insert_student('John Doe', 20)