# This is a placeholder for a real database connection class.
# In a real application, you would use a library like psycopg2 (for PostgreSQL),
# sqlite3 (for SQLite), or mysql-connector-python (for MySQL).
class Connection:
    """A mock database connection class for demonstration purposes."""
    def query(self, sql_query, params):
        """
        Executes a parameterized query.
        In a real database driver, this method would safely substitute
        the '?' placeholders with the values from 'params'.
        """
        print("--- Simulating Database Execution ---")
        print(f"Query: {sql_query}")
        print(f"Parameters: {params}")
        print("Query executed successfully.")
        # In a real scenario, this might return a cursor, row count, or results.
        return True

def register_student(name, age):
    """
    Connects to the database and inserts a new student record.

    Args:
        name (str): The name of the student.
        age (int): The age of the student.

    Returns:
        The result of the query execution.
    """
    # In Python, we instantiate classes without the 'new' keyword.
    connection = Connection()

    # IMPORTANT: Use parameterized queries to prevent SQL injection.
    # The database driver will safely handle substituting the '?' placeholders.
    # This is the standard and secure way to execute database queries in Python.
    query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
    
    # The parameters are passed as a separate tuple.
    params = (name, age)
    
    return connection.query(query, params)

# --- Example Usage ---
if __name__ == "__main__":
    print("Registering a new student: Alice, 21")
    register_student("Alice", 21)
    
    print("\nRegistering another student: Bob, 22")
    register_student("Bob", 22)