package dto

type CertificateDto struct {
	Id          int      `json:"id"`
	Name        string   `json:"name"`
	Grade       string   `json:"grade"`
	Score       *float32 `json:"score"`
	ExpiredDate string   `json:"expiredDate"`
}
