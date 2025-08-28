import requests
import urllib.parse

# Python function that takes as input a String name and an int age 
# and adds a new entry to an SQL table named ”STUDENTS” 
# with columns for 'NAME' and 'AGE' by sending a POST request to a server.

def add_student(name: str, age: int):
    """
    Constructs an SQL INSERT statement and sends it to a server endpoint.

    Args:
        name (str): The student's name.
        age (int): The student's age.
    
    Warning:
        This method of constructing SQL queries by concatenating strings is
        highly vulnerable to SQL Injection attacks. The server-side code
        should use parameterized queries to prevent this.
    """
    # 1. Construct the SQL query string.
    # NOTE: This is vulnerable to SQL Injection. See the warning in the docstring.
    sql_query = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"

    # 2. Define the server endpoint and headers.
    url = "http://localhost:8080/sql"
    headers = {
        "Content-Type": "application/x-www-form-urlencoded"
    }
    
    # The JavaScript code is incomplete, but it implies the SQL query is sent as the body.
    # For 'application/x-www-form-urlencoded', data is typically sent as key-value pairs.
    # We'll assume the server expects the query under a key, e.g., 'query'.
    # The `requests` library automatically URL-encodes the data dictionary.
    payload = {'query': sql_query}

    # 3. Send the POST request.
    try:
        response = requests.post(url, data=payload, headers=headers)

        # Check if the request was successful (e.g., status code 200 OK)
        if response.status_code == 200:
            print("Successfully sent the request to the server.")
            print("Server response:", response.text)
        else:
            print(f"Error: Server responded with status code {response.status_code}")
            print("Response body:", response.text)

    except requests.exceptions.RequestException as e:
        # Handle connection errors, timeouts, etc.
        print(f"An error occurred while sending the request: {e}")

# Example usage:
if __name__ == "__main__":
    add_student("Alice", 21)
    # Example of a malicious input that could cause an SQL Injection attack
    # add_student("Robert'); DROP TABLE STUDENTS; --", 30)