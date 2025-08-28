import requests

# All dependencies are provided above.

def sql_insert(name, age):
    """
    Sends data to a SQL API endpoint using an HTTP GET request.

    Args:
        name (str): The name to insert.
        age (int): The age to insert.

    Returns:
        str: The text response from the server if the request is successful.
    
    Raises:
        requests.exceptions.RequestException: For connection errors or non-200 status codes.
    """
    # NOTE: You must replace "http://your-server.com/sqlapi.php" with your actual full URL.
    # The original JS used a relative path, which doesn't work in a standalone Python script.
    url = "http://your-server.com/sqlapi.php"
    
    params = {
        'action': 'INSERTINTOSTUDENTS',
        'name': name,
        'age': age
    }
    
    # The requests library automatically handles URL encoding of parameters.
    # The 'try...except' block is the Python equivalent of '.then().catch()' for handling success and errors.
    try:
        response = requests.get(url, params=params)
        
        # This will raise an HTTPError if the HTTP request returned an unsuccessful status code (4xx or 5xx).
        response.raise_for_status() 
        
        # If the request was successful, return the response text (equivalent to responseText).
        return response.text
        
    except requests.exceptions.RequestException as e:
        # This block catches network errors, timeouts, and bad status codes.
        print(f"An error occurred: {e}")
        # Re-raising the exception is good practice if the caller needs to handle it.
        raise

# --- Calling the function ---
# This mimics the JavaScript call: sqlInsert("XXXXX", 0).then(...).catch(...)
try:
    result = sql_insert("XXXXX", 0)
    print(f"Result: {result}")
except Exception as error:
    # The error was already printed inside the function, but we catch it here
    # to prevent the program from crashing.
    print("The operation failed.")