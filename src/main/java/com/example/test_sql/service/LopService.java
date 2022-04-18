package com.example.test_sql.service;

import com.example.test_sql.dto.LopDTO;
import com.example.test_sql.mapper.LopMapper;
import com.example.test_sql.model.Khoa;
import com.example.test_sql.model.Lop;
import com.example.test_sql.model.Sinhvien;
import com.example.test_sql.repository.CustomeRepository;
import com.example.test_sql.repository.KhoaRepository;
import com.example.test_sql.repository.LopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LopService {

    @Autowired
    private CustomeRepository customeRepository;
    @Autowired
    LopRepository lopRepository;

    @Autowired
    KhoaRepository khoaRepository;

    @Autowired
    LopMapper lopMapper;

    public List<LopDTO> getList(Pageable pageable) {
        List<Lop> lopList = lopRepository.findAll(pageable).toList();
        List<LopDTO> lopDTOList = new ArrayList<LopDTO>();
        for (Lop lop : lopList) {
            LopDTO lopDTO = lopMapper.toDTO(lop);
            lopDTOList.add(lopDTO);
        }

        return lopDTOList;
    }
    public List<LopDTO> List() {
        List<Lop> lopList = lopRepository.findAll();
        List<LopDTO> lopDTOList = new ArrayList<LopDTO>();
        for (Lop lop : lopList) {
            LopDTO lopDTO = lopMapper.toDTO(lop);
            lopDTOList.add(lopDTO);
        }

        return lopDTOList;
    }

    public LopDTO getone(String malop) {
        Optional<Lop> lopOptional = lopRepository.findById(malop);
        LopDTO lopDTO = lopMapper.toDTO(lopOptional.get());
        if (lopOptional.isPresent()) {
            return lopDTO;
        } else {
            throw new RuntimeException("Không tồn tại bản ghi " + malop);
        }
    }

    public void post(LopDTO lopDTO) {
        Optional<Lop> lopOptional = lopRepository.findById(lopDTO.getMaLop());
        if (lopOptional.isPresent()) {
            throw new RuntimeException("Đã tồn tại bản ghi ");
        } else {
            Khoa khoaLop = khoaRepository.khoaByName(lopDTO.getTenKhoa());
            Lop lop = lopMapper.toLop(lopDTO);
            lop.setTenKhoa(khoaLop);
            lopRepository.save(lop);
        }
    }

    public List<LopDTO> search(String search) {
        List<Lop> lopList = lopRepository.search(search);
        List<LopDTO> lopDTOList = new ArrayList<LopDTO>();
        for (Lop lop : lopList) {
            LopDTO lopDTO = lopMapper.toDTO(lop);
            lopDTOList.add(lopDTO);
        }
        return lopDTOList;
    }

    public void put(LopDTO lopDTO,String malop) {
        Optional<Lop> optionalLop = lopRepository.findById(malop);
        if (optionalLop.isPresent()) {
            Khoa khoaLop = khoaRepository.khoaByName(lopDTO.getTenKhoa());
            optionalLop.get().setTenLop(lopDTO.getTenLop());
            optionalLop.get().setTenKhoa(khoaLop);
            lopRepository.save(optionalLop.get());
        } else {
            throw new RuntimeException("Không tồn tại bản ghi");
        }
    }

    public void delete(String malop) {
        Optional<Lop> deletelop = lopRepository.findById(malop);
        List<Sinhvien> sinhviens = deletelop.get().getSinhviens();

        if ( sinhviens.size() <= 0) {
            lopRepository.deleteById(malop);

        } else {
            throw new RuntimeException("Không hợp lệ ");
        }

    }


}
