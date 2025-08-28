import sqlite3
from sqlite3 import Error

class Database:
    def __init__(self, db_file):
        """
        Initialize a Database object.

        :param db_file: The path to the SQLite database file.
        """
        self.conn = None
        try:
            self.conn = sqlite3.connect(db_file)
            print(f"Connected to SQLite Database {db_file}")
        except Error as e:
            print(e)

    def create_table(self):
        """
        Create a table named 'students' with columns 'id', 'name', and 'age'.
        """
        sql = """CREATE TABLE IF NOT EXISTS students (
                    id integer PRIMARY KEY,
                    name text NOT NULL,
                    age integer NOT NULL
                );"""
        try:
            cursor = self.conn.cursor()
            cursor.execute(sql)
            self.conn.commit()
            print("Table created successfully")
        except Error as e:
            print(f"Error occurred while creating the table: {e}")

    def insert_student(self, name, age):
        """
        Insert a student into the 'students' table.

        :param name: The student's name.
        :param age: The student's age.
        """
        sql = """INSERT INTO students(name, age) VALUES (?, ?)"""
        try:
            cursor = self.conn.cursor()
            cursor.execute(sql, (name, age))
            self.conn.commit()
            print(f"Student '{name}' inserted successfully")
        except Error as e:
            print(f"Error occurred while inserting the student: {e}")

    def close_connection(self):
        """
        Close the database connection.
        """
        if self.conn:
            self.conn.close()
            print("Database connection closed")


# Example usage
if __name__ == "__main__":
    db = Database('mydb.db')
    db.create_table()
    db.insert_student('John Doe', 20)
    db.close_connection()