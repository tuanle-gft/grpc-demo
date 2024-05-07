class CertificateDto {
  const CertificateDto(
      this.id, this.name, this.grade, this.score, this.expiredDate);

  final int id;
  final String name;
  final String grade;
  final double? score;
  final int? expiredDate;
}
