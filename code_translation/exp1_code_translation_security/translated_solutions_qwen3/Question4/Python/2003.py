import sqlite3

def insert_student(conn, name, age):
    conn.execute("INSERT INTO students (name, age) VALUES (:name, :age)", name=name, age=age)

def main():
    # Connect to an in-memory SQLite database
    conn = sqlite3.connect(':memory:')
    
    # Create the students table
    conn.execute("CREATE TABLE students (name TEXT, age INT)")
    
    # Insert a student
    insert_student(conn, "XXXXX", 0)
    
    # Query and print all students
    for row in conn.execute("SELECT * FROM students"):
        print(row)
    
    # Close the database connection
    conn.close()

if __name__ == "__main__":
    main()