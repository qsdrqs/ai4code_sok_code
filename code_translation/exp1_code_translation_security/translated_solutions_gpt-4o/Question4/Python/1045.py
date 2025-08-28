import requests
import asyncio

async def sql_insert(name, age):
    try:
        url = f"sqlapi.php?action=INSERTINTOSTUDENTS&&name={name}&&age={age}"
        response = requests.get(url)
        if response.status_code == 200:
            return response.text
        else:
            raise Exception(f"HTTP Error: {response.status_code}")
    except Exception as e:
        raise e

async def main():
    try:
        result = await sql_insert("XXXXX", 0)
        print("result", result)
    except Exception as error:
        print("error", error)

# Run the main function
asyncio.run(main())