import locale

# Set the numeric formatting locale to the system's default
locale.setlocale(locale.LC_NUMERIC, '')

# Define the number to be formatted
num = 7000000

# Format the number with thousand separators using the current locale
formatted_num = locale.format_string("%d", num, grouping=True)

# Print the formatted number
print(formatted_num)