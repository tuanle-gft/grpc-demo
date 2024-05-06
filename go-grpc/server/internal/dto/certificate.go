package dto

type Certificate struct {
	Id          int      `json:"id"`
	EmployeeId  int      `json:"employeeId"`
	Name        string   `json:"name"`
	Grade       string   `json:"grade"`
	Score       *float32 `json:"score"`
	ExpiredDate string   `json:"expiredDate"`
}
