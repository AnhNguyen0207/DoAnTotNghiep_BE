package doan.quanlykho.be.service;

import doan.quanlykho.be.dto.request.TransportCompaniesDTO;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ITransportCompaniesService {
    List<TransportCompaniesDTO> findAll(Integer pageNumber, Integer limit, String sortBy);

    TransportCompaniesDTO findById(Integer id);

    TransportCompaniesDTO create(TransportCompaniesDTO transportCompaniesDTO, BindingResult bindingResult);

    TransportCompaniesDTO update(Integer id, TransportCompaniesDTO transportCompaniesDTO, BindingResult bindingResult);
}

