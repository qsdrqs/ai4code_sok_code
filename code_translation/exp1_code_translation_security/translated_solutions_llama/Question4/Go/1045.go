package main

import (
    "bytes"
    "encoding/json"
    "fmt"
    "io/ioutil"
    "log"
    "net/http"
)

// SQLInsertResponse represents the response from the SQL API
type SQLInsertResponse struct {
    Result string `json:"result"`
}

func sqlInsert(name string, age int) (*SQLInsertResponse, error) {
    url := fmt.Sprintf("http://example.com/sqlapi.php?action=INSERTINTOSTUDENTS&&name=%s&&age=%d", name, age)
    resp, err := http.Get(url)
    if err != nil {
        return nil, err
    }
    defer resp.Body.Close()

    if resp.StatusCode != http.StatusOK {
        return nil, fmt.Errorf("HTTP request failed with status code %d", resp.StatusCode)
    }

    body, err := ioutil.ReadAll(resp.Body)
    if err != nil {
        return nil, err
    }

    var response SQLInsertResponse
    err = json.Unmarshal(body, &response)
    if err != nil {
        return nil, err
    }

    return &response, nil
}

func main() {
    name := "XXXXX"
    age := 0

    response, err := sqlInsert(name, age)
    if err != nil {
        log.Printf("Error: %v\n", err)
        return
    }

    log.Printf("Result: %s\n", response.Result)
}