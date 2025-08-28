import asyncio
import aiohttp


async def sql_insert(name: str, age: int) -> str:
    """
    Sends a GET request that mimics the JavaScript sqlInsert function and
    returns the server’s response text.
    """
    url = f"sqlapi.php?action=INSERTINTOSTUDENTS&&name={name}&&age={age}"

    async with aiohttp.ClientSession() as session:
        async with session.get(url) as resp:
            resp.raise_for_status()          # ensure HTTP 200-series status
            return await resp.text()         # equivalent to XMLHttpRequest.responseText


async def main() -> None:
    try:
        result = await sql_insert("XXXXX", 0)
        print("result", result)
    except Exception as err:
        print("error", err)


if __name__ == "__main__":
    asyncio.run(main())