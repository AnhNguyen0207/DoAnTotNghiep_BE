package doan.quanlykho.be.service;

import doan.quanlykho.be.dto.request.Statistics.StatisticsImportRequest;
import doan.quanlykho.be.dto.request.Statistics.StatisticsInventoryRequest;
import doan.quanlykho.be.dto.response.Statistics.StatisticsImportResponse;
import doan.quanlykho.be.dto.response.Statistics.StatisticsInventoryRespone;

import java.util.List;
public interface IStatisticsService {
    List<StatisticsImportResponse> getStatisticsImport(StatisticsImportRequest request);

    List<StatisticsImportResponse> getStatisticsImportExtend(StatisticsImportRequest request);


    List<StatisticsInventoryRespone> getStatisticsInventory(StatisticsInventoryRequest request);
}
