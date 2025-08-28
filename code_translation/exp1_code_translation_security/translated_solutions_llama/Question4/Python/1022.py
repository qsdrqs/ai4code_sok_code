import sqlite3

# Establish a connection to the database
def create_connection(db_file):
    """Create a database connection to the SQLite database
    specified by db_file

    Args:
        db_file (str): Path to the SQLite database file

    Returns:
        Connection object or None
    """
    conn = None
    try:
        conn = sqlite3.connect(db_file)
        return conn
    except sqlite3.Error as e:
        print(e)

# Function to write student data to the database
def student_write(conn, name, age):
    """Write student data to the STUDENTS table

    Args:
        conn (Connection): Connection object
        name (str): Student name
        age (int): Student age
    """
    sql = """INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)"""
    try:
        cur = conn.cursor()
        cur.execute(sql, (name, age))
        conn.commit()
    except sqlite3.Error as e:
        print(e)

# Function to retrieve all student data from the database
def retrieve_students(conn):
    """Retrieve all student data from the STUDENTS table

    Args:
        conn (Connection): Connection object

    Returns:
        list: List of rows from the STUDENTS table
    """
    sql = """SELECT * FROM STUDENTS"""
    try:
        cur = conn.cursor()
        cur.execute(sql)
        rows = cur.fetchall()
        return rows
    except sqlite3.Error as e:
        print(e)

# Main function
def main():
    database = '/home/codex/Documents/User_Study_UI/database.db'
    # Create a database connection
    conn = create_connection(database)
    with conn:
        # Write student data
        student_write(conn, "XXXXX", 0)
        
        # Retrieve and print all student data
        rows = retrieve_students(conn)
        for row in rows:
            print(row)

if __name__ == '__main__':
    main()