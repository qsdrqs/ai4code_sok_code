import requests
import json

def insert_student(name, age):
    """
    Insert a new student entry into the STUDENTS table via HTTP POST request.
    
    Args:
        name (str): Student's name
        age (int): Student's age
    """
    try:
        response = requests.post(
            url="/students",
            data={
                "name": name,
                "age": age
            }
        )
        
        # Check if request was successful
        if response.status_code == 200:
            print(response.text)
        else:
            print(f"Error: {response.status_code} - {response.text}")
            
    except requests.exceptions.RequestException as e:
        print(f"Request failed: {e}")

# Example usage
insert_student('XXXXX', 0)