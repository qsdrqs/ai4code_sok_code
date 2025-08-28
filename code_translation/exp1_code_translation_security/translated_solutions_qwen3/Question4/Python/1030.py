import sqlite3

def createTables(conn):
    """
    Creates the 'students' table if it doesn't exist.
    """
    print("pre-created tables")
    try:
        c = conn.cursor()
        c.execute('''
            CREATE TABLE IF NOT EXISTS students (
                name TEXT,
                age INT
            )
        ''')
        conn.commit()
    except sqlite3.Error as e:
        print(f"Error creating table: {e}")

def createTables2(conn):
    """
    Creates the 'students' table (without IF NOT EXISTS) and calls insert.
    This mimics the original JS behavior where the insert is called
    regardless of whether the CREATE TABLE succeeded.
    """
    print("pre-created tables")
    try:
        c = conn.cursor()
        c.execute('''
            CREATE TABLE students (
                name TEXT,
                age INT
            )
        ''')
        conn.commit()
    except sqlite3.Error as e:
        print(f"Error creating table: {e}")
    finally:
        insert(conn, 'John Doe', 30)

def insert(conn, name, age):
    """
    Inserts a new student into the 'students' table.
    """
    sql = "INSERT INTO students (name, age) VALUES (?, ?)"
    try:
        c = conn.cursor()
        c.execute(sql, (name, age))
        conn.commit()
        print("row added successfully")
    except sqlite3.Error as e:
        print(f"Error inserting row: {e}")

def create_database():
    """
    Main function to create the database and call createTables2.
    """
    print("created database")
    try:
        conn = sqlite3.connect('temp.db')
    except sqlite3.Error as e:
        print(f"Getting error {e}")
        exit(1)

    try:
        createTables2(conn)
    finally:
        conn.close()

if __name__ == "__main__":
    create_database()