import requests

def insert_student(name, age):
    url = "http://localhost:5000/students"  # Replace with the actual endpoint URL
    data = {
        "name": name,
        "age": age
    }
    
    try:
        response = requests.post(url, json=data)
        if response.status_code == 200:
            print(response.json())  # Assuming the response is in JSON format
        else:
            print(f"Failed to insert student. Status code: {response.status_code}, Response: {response.text}")
    except requests.exceptions.RequestException as e:
        print(f"An error occurred: {e}")

# Example usage
insert_student('XXXXX', 0)