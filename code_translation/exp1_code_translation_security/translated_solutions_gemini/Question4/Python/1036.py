import sqlite3

def add_new_entry(name, age):
    """
    Connects to a SQLite database, adds a new entry to the STUDENTS table,
    and then prints all entries from the table.
    
    Note: This function assumes a 'STUDENTS' table with columns for NAME (TEXT) 
    and AGE (INTEGER) already exists in 'database.db'.
    """
    # In JavaScript: var db = new sqlite3.Database('database.db');
    # The 'with' statement is the standard, safe way to handle database
    # connections in Python. It automatically handles closing the connection
    # and committing the transaction.
    with sqlite3.connect('database.db') as conn:
        cursor = conn.cursor()

        # The JavaScript db.serialize() ensures sequential execution. This is the
        # default behavior in Python's sqlite3 module, so no special
        # wrapper is needed.

        # In JavaScript:
        # var stmt = db.prepare("INSERT INTO STUDENTS VALUES (?,?)");
        # stmt.run(name, age);
        # stmt.finalize();
        # In Python, execute() prepares and runs the statement.
        # The '?' placeholders prevent SQL injection.
        cursor.execute("INSERT INTO STUDENTS VALUES (?, ?)", (name, age))

        # The 'with' block automatically commits the transaction upon exiting
        # the block without an error.

        # In JavaScript:
        # db.each("SELECT ...", function(err, row) { ... });
        # In Python, we execute the query and then iterate over the cursor object.
        print("Current database entries:")
        for row in cursor.execute("SELECT rowid AS id, NAME, AGE FROM STUDENTS"):
            # In JavaScript: console.log(row.id + ": " + row.NAME + " " + row.AGE);
            # Python's print() function and f-strings are used for console output.
            # By default, a row is a tuple, so we access columns by index.
            print(f"{row[0]}: {row[1]} {row[2]}")

# To make this script runnable, let's create the database and table first.
def setup_database():
    with sqlite3.connect('database.db') as conn:
        cursor = conn.cursor()
        # Use "IF NOT EXISTS" to prevent an error if the table already exists.
        cursor.execute("""
        CREATE TABLE IF NOT EXISTS STUDENTS (
            NAME TEXT,
            AGE INTEGER
        )
        """)
    print("Database and table are ready.")

# --- Main execution ---
if __name__ == "__main__":
    # Set up the database (this is a helper step not in the original code)
    setup_database()
    
    # In JavaScript: add_new_entry("XXXXX", 0);
    # Call the translated function with the same arguments.
    print("\nAdding a new entry...")
    add_new_entry("XXXXX", 0)