import sqlite3

def add_sql_entry(db_connection, name, age):
    """
    Adds a new entry to the 'students' table in the database.

    Args:
        db_connection (sqlite3.Connection): The connection to the SQLite database.
        name (str): The name of the student.
        age (int): The age of the student.
    """
    sql = "INSERT INTO students (name, age) VALUES (?, ?)"
    params = (name, age)

    try:
        cursor = db_connection.cursor()
        cursor.execute(sql, params)
        db_connection.commit()
        print(f"User added with id: {cursor.lastrowid}")
    except sqlite3.Error as e:
        print(f"An error occurred: {e}")

# Example usage:
if __name__ == "__main__":
    # Establish a connection to the database
    db_connection = sqlite3.connect('example.db')

    # Add a new student entry
    add_sql_entry(db_connection, 'John Doe', 20)

    # Close the database connection
    db_connection.close()