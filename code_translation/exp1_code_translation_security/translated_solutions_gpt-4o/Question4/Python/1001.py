import sqlite3

def add_sql_entry(name, age):
    sql = "INSERT INTO students (name, age) VALUES (?, ?)"
    params = (name, age)
    
    # Assuming 'db' is a connection object
    with db:
        try:
            cursor = db.execute(sql, params)
            print("User added with id:", cursor.lastrowid)
        except sqlite3.Error as err:
            print(err)

# Example usage:
# db = sqlite3.connect('your_database.db')
# add_sql_entry('John Doe', 25)