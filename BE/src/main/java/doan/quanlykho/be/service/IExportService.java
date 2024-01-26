package doan.quanlykho.be.service;

import doan.quanlykho.be.base.IBaseService;
import doan.quanlykho.be.base.ResponseListDto;
import doan.quanlykho.be.entity.Export;

public interface IExportService extends IBaseService<Export> {
    ResponseListDto<Export> findExportByAll(Integer exportInventory, Integer receiveInventory,
                                            Integer status, String code, Boolean cancel, Integer page,
                                            Integer perPage, String sort, String sortBy);
}
