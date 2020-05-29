package ro.utcn.wasteless.report.service;

import ro.utcn.wasteless.domain.User;
import ro.utcn.wasteless.dto.reports.ReportDto;

import java.util.Date;

public interface ReportService {
    ReportDto getAllReports(User user, Date date);
}
