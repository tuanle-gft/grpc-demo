import 'package:fixnum/fixnum.dart' as $fixnum;

class CertificateDto {
  const CertificateDto(
      this.id, this.name, this.grade, this.score, this.expiredDate);

  final int id;
  final String name;
  final String grade;
  final double? score;
  final $fixnum.Int64? expiredDate;
}
