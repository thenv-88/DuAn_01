package com.example.test_sql.service;

import com.example.test_sql.dto.BaiThiDTO;
import com.example.test_sql.mapper.BaiThiMapper;
import com.example.test_sql.model.Baithi;
import com.example.test_sql.model.Monhoc;
import com.example.test_sql.model.Sinhvien;
import com.example.test_sql.repository.BaiThiRepository;
import com.example.test_sql.repository.MonHocRepository;
import com.example.test_sql.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BaiThiService {
    @Autowired
    BaiThiMapper baiThiMapper;

    @Autowired
    BaiThiRepository baiThiRepository;

    @Autowired
    SinhVienRepository sinhVienRepository;

    @Autowired
    MonHocRepository monHocRepository;

    public List<BaiThiDTO> getlist(Pageable pageable){
        List<Baithi> baithis =  baiThiRepository.findAll(pageable).getContent();
        List<BaiThiDTO> baiThiDTOList = new ArrayList<BaiThiDTO>();
        for (Baithi baithi: baithis) {
            BaiThiDTO baiThiDTO = baiThiMapper.baiThiDTO(baithi);
            baiThiDTOList.add(baiThiDTO);

        }
        return baiThiDTOList;
    }

    public List<BaiThiDTO> List(){
        List<Baithi> baithis =  baiThiRepository.findAll();
        List<BaiThiDTO> baiThiDTOList = new ArrayList<BaiThiDTO>();
        for (Baithi baithi: baithis) {
            BaiThiDTO baiThiDTO = baiThiMapper.baiThiDTO(baithi);
            baiThiDTOList.add(baiThiDTO);

        }
        return baiThiDTOList;
    }
    public List<BaiThiDTO> search(Integer diem){
        List<Baithi> baithis =  baiThiRepository.searchdiem(diem);
        List<BaiThiDTO> baiThiDTOList = new ArrayList<BaiThiDTO>();
        for (Baithi baithi: baithis) {
            BaiThiDTO baiThiDTO = baiThiMapper.baiThiDTO(baithi);
            baiThiDTOList.add(baiThiDTO);

        }
        return baiThiDTOList;
    }

    public BaiThiDTO getone(Long mabaithi){
        return baiThiMapper.baiThiDTO(baiThiRepository.findById(mabaithi).get());
    }



    public BaiThiDTO  post(BaiThiDTO baiThiDTO){
            Optional<Sinhvien> sinhvien = sinhVienRepository.findById(baiThiDTO.getMaSinhVien());
            Optional<Monhoc> monhoc = monHocRepository.findById(baiThiDTO.getMaMonHoc());
            Baithi baithi1 = new Baithi();
            baithi1.setDiem(baiThiDTO.getDiem());
            baithi1.setMaMonHoc(monhoc.get());
            baithi1.setMaSinhVien(sinhvien.get());
            baiThiRepository.save(baithi1);
            return baiThiDTO;
    }

    public BaiThiDTO put(BaiThiDTO baiThiDTO,Long mabaithi){
        Optional<Baithi> baithi = baiThiRepository.findById(mabaithi);
        Optional<Sinhvien> sinhvien = sinhVienRepository.findById(baiThiDTO.getMaSinhVien());
        Optional<Monhoc> monhoc = monHocRepository.findById(baiThiDTO.getMaMonHoc());
        Baithi baithiput = baiThiMapper.baithi(baiThiDTO);
        baithiput.setMaSinhVien(sinhvien.get());
        baithiput.setMaMonHoc(monhoc.get());
        baithi.get().setDiem(baithiput.getDiem());
        baithi.get().setMaMonHoc(baithiput.getMaMonHoc());
        baithi.get().setMaSinhVien(baithiput.getMaSinhVien());
        baiThiRepository.save(baithi.get());
        return baiThiDTO;
    }

    public void delete(Long mabaithi){
            baiThiRepository.deleteById(mabaithi);
    }
}
