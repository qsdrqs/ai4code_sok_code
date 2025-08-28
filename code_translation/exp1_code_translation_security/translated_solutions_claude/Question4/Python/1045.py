import asyncio
import aiohttp

async def sql_insert(name, age):
    """
    Async function to insert data via HTTP GET request
    Returns the response text if successful
    """
    try:
        async with aiohttp.ClientSession() as session:
            url = f"sqlapi.php?action=INSERTINTOSTUDENTS&&name={name}&&age={age}"
            async with session.get(url) as response:
                if response.status == 200:
                    return await response.text()
                else:
                    raise Exception(f"HTTP Error: {response.status}")
    except Exception as e:
        raise e

# Usage example
async def main():
    try:
        result = await sql_insert("XXXXX", 0)
        print("result", result)
    except Exception as error:
        print("error", error)

# Run the async function
if __name__ == "__main__":
    asyncio.run(main())