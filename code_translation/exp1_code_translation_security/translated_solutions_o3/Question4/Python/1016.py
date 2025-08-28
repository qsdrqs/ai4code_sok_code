import requests

def add_student(name, age):
    """
    Takes a student's name (str) and age (int) and inserts the data
    into the STUDENTS table via an HTTP POST request.

    NOTE: This translation mirrors the original logic and therefore
    still builds the SQL string by simple concatenation.  In real
    code you should use parameterized queries or an ORM to avoid
    SQL-injection vulnerabilities.
    """

    # Build the SQL statement
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"

    # Define request parameters
    url     = "http://localhost:8080/sql"
    headers = {"Content-Type": "application/x-www-form-urlencoded"}

    # Send the request
    response = requests.post(url, headers=headers, data=sql)

    # Basic error handling
    response.raise_for_status()

    return response.text        # Or simply `return` / `pass` if no response is needed