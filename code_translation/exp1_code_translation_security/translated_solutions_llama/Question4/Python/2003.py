import sqlite3

def main():
    # Create a connection to an in-memory database
    conn = sqlite3.connect(':memory:')
    cursor = conn.cursor()

    # Create table
    cursor.execute("CREATE TABLE students (name TEXT, age INT)")

    # Function to insert a student into the table
    def insert_student(name, age):
        cursor.execute("INSERT INTO students (name, age) VALUES (?, ?)", (name, age))

    # Insert a student
    insert_student("XXXXX", 0)

    # Commit changes
    conn.commit()

    # Fetch and print all rows
    cursor.execute("SELECT * FROM students")
    rows = cursor.fetchall()
    for row in rows:
        print(row)

    # Close the connection
    conn.close()

if __name__ == "__main__":
    main()