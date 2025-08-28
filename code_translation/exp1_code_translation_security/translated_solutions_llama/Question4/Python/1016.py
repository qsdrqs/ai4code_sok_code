import requests

def add_student(name: str, age: int) -> None:
    """
    Adds a new student to the STUDENTS table in the SQL database.

    Args:
    - name (str): The name of the student.
    - age (int): The age of the student.
    """
    sql = f"INSERT INTO STUDENTS (NAME, AGE) VALUES ('{name}', {age})"
    data = f"sql={sql}"

    try:
        response = requests.post(
            "http://localhost:8080/sql",
            data=data,
            headers={"Content-type": "application/x-www-form-urlencoded"}
        )
        response.raise_for_status()  # Raise an exception for HTTP errors
    except requests.exceptions.RequestException as e:
        print(f"An error occurred: {e}")

# Example usage:
if __name__ == "__main__":
    add_student("John Doe", 20)