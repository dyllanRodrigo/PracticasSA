package main

import (
	"fmt"
	"net/http"
)

func getTreatments(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Listado de tratamientos")
}

func main() {
	http.HandleFunc("/treatments", getTreatments)
	http.ListenAndServe(":3003", nil)
}
