import sqlite3
import time
import logging

# Configure logging
logging.basicConfig(level=logging.INFO)

def create_database():
    """Create a new SQLite database."""
    try:
        conn = sqlite3.connect('temp.db')
        logging.info("Database created")
        return conn
    except sqlite3.Error as e:
        logging.error(f"Error creating database: {e}")
        exit(1)

def create_tables(conn):
    """Create a new table in the database."""
    try:
        cursor = conn.cursor()
        cursor.execute('CREATE TABLE IF NOT EXISTS students(name TEXT, age INT)')
        conn.commit()
        logging.info("Tables created")
    except sqlite3.Error as e:
        logging.error(f"Error creating tables: {e}")

def insert(conn, name, age):
    """Insert a new record into the students table."""
    try:
        cursor = conn.cursor()
        sql = "INSERT INTO students(name, age) VALUES (?, ?)"
        cursor.execute(sql, (name, age))
        conn.commit()
        logging.info("Row added successfully")
    except sqlite3.Error as e:
        logging.error(f"Error inserting record: {e}")

def main():
    # Create database and tables
    conn = create_database()
    create_tables(conn)

    # Insert record after a short delay
    time.sleep(0.5)
    insert(conn, 'John Doe', 30)

    # Retrieve and print all records after a short delay
    time.sleep(0.5)
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM students")
    rows = cursor.fetchall()
    for row in rows:
        logging.info(f"{row[0]} {row[1]}")

    # Close the database connection
    conn.close()

if __name__ == "__main__":
    main()