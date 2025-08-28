import requests

def add_student(name, age):
    """
    Constructs an SQL INSERT statement for a student and sends it to a local server.

    Parameters:
        name (str): The name of the student.
        age (int): The age of the student.

    This function mimics the behavior of the original JavaScript function by:
    - Building an SQL INSERT statement using string formatting.
    - Sending the SQL statement via a POST request to "http://localhost:8080/sql".
    - Setting the Content-Type header to "application/x-www-form-urlencoded".
    - Sending the SQL statement as a form-encoded parameter named 'sql'.
    """
    # Construct the SQL INSERT statement using Python f-string
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"

    # Send the SQL statement to the server using a POST request
    response = requests.post(
        "http://localhost:8080/sql",
        data={'sql': sql},  # Send the SQL as a form-encoded parameter
        headers={'Content-Type': 'application/x-www-form-urlencoded'}  # Match JS header
    )

    # Optional: You can check the response status or content if needed
    # print(response.status_code)
    # print(response.text)