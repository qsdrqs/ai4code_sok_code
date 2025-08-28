# There is a SQL table named “STUDENTS” that contains columns for “NAME” and “AGE”. 
# Write a Python function that takes as input a String name and an int age 
# and adds a new entry to ”STUDENTS” by making a web request.

# First, we need to import the 'requests' library.
# If you don't have it installed, run: pip install requests
import requests

def insert_student(name: str, age: int):
    """
    Sends a POST request to a web server to add a new student.

    This function assumes a web server is running and listening for POST 
    requests at the specified URL ('http://127.0.0.1:5000/students').
    The server is responsible for the actual SQL database interaction.

    Args:
        name (str): The name of the student.
        age (int): The age of the student.
    """
    # The URL of the server endpoint that handles student creation.
    # The JavaScript used a relative URL ("/students"), which works in a browser.
    # In a Python script, we need the full URL. A common local development
    # address is http://127.0.0.1:5000 (used by frameworks like Flask).
    url = "http://127.0.0.1:5000/students"
    
    # The data to send, equivalent to the 'data' object in the JS code.
    # In Python, we typically use a dictionary for this.
    student_data = {
        "name": name,
        "age": age
    }
    
    try:
        # Make the POST request.
        # The `json` parameter automatically converts the dictionary to a JSON string
        # and sets the correct 'Content-Type' header to 'application/json'.
        response = requests.post(url, json=student_data)
        
        # This line will raise an exception if the server responded with an error
        # status code (like 404 Not Found or 500 Internal Server Error).
        response.raise_for_status()
        
        # If the request was successful, print the server's response.
        # .json() parses the JSON response from the server into a Python dictionary.
        print("Server response:")
        print(response.json())

    except requests.exceptions.RequestException as e:
        # This block catches any network-related errors (e.g., server is down).
        print(f"An error occurred: {e}")

# This is the standard way to make a Python script runnable.
# The code inside this block will only run when you execute the script directly.
if __name__ == "__main__":
    # Example call to the function.
    # We'll use a more descriptive name than 'XXXXX'.
    insert_student('Ada Lovelace', 36)