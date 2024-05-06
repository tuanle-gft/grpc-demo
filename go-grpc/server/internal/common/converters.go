package common

import (
	"time"

	"gft.com/prince-bank-grpc-demo/go-grpc/server/internal/dto"
	pb "gft.com/prince-bank-grpc-demo/go-grpc/service-def"
)

func ToPbEmployee(apiEmployee dto.EmployeeDto) pb.Employee {
	pbEmployee := pb.Employee{
		Id:           int32(apiEmployee.Id),
		Name:         apiEmployee.Name,
		EmployeeType: apiEmployee.EmployeeType,
	}
	if apiEmployee.Dob != nil {
		dobStringVal := *apiEmployee.Dob
		dobVal, _ := time.Parse("2006-01-02", dobStringVal)
		pbEmployee.Dob = ToPointer(dobVal.UnixMilli())
	}
	if len(apiEmployee.Certificates) > 0 {
		pbCertificates := make([]*pb.Certificate, 0)
		for _, apiCertificate := range apiEmployee.Certificates {
			pbCertificate := &pb.Certificate{
				Id:    int32(apiCertificate.Id),
				Name:  apiCertificate.Name,
				Grade: apiCertificate.Grade,
				Score: apiCertificate.Score,
			}
			expiredDateStringVal := apiCertificate.ExpiredDate
			expiredDateVal, _ := time.Parse("2006-01-02", expiredDateStringVal)
			pbCertificate.ExpiredDate = expiredDateVal.UnixMilli()
			pbCertificates = append(pbCertificates, pbCertificate)
		}
		pbEmployee.Certificates = pbCertificates
	}
	return pbEmployee
}
