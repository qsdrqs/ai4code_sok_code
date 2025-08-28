int main() {
    // Assume active connection
    TDS_CONN *conn = /* initialize connection */;

    const char *name = "John Doe";
    int age = 25;

    insert_user(conn, name, age);

    return 0;
}