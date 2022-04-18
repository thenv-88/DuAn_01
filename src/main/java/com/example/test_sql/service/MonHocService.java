package com.example.test_sql.service;

import com.example.test_sql.dto.MonHocDTO;
import com.example.test_sql.mapper.MonHocMapper;
import com.example.test_sql.model.Baithi;
import com.example.test_sql.model.Monhoc;
import com.example.test_sql.repository.MonHocRepository;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MonHocService {
    @Autowired
    MonHocRepository monHocRepository ;


    @Autowired
    MonHocMapper monHocMapper;

    public List<MonHocDTO> getList(Pageable pageable){
        List<Monhoc> monhocList = monHocRepository.findAll(pageable).toList();
        List<MonHocDTO> monHocDTOList = new ArrayList<>();
        for (Monhoc monhoc : monhocList) {
            MonHocDTO monHocDTO = monHocMapper.monHocDTO(monhoc);
            monHocDTOList.add(monHocDTO);
        }
        return monHocDTOList;
    }

    public List<MonHocDTO> List(){
        List<Monhoc> monhocList = monHocRepository.findAll();
        List<MonHocDTO> monHocDTOList = new ArrayList<>();
        for (Monhoc monhoc : monhocList) {
            MonHocDTO monHocDTO = monHocMapper.monHocDTO(monhoc);
            monHocDTOList.add(monHocDTO);
        }
        return monHocDTOList;
    }

    public MonHocDTO getone(String MaMonhoc){
        Optional<Monhoc> monhoc = monHocRepository.findById(MaMonhoc);
        if(monhoc.isPresent()){
            MonHocDTO monHocDTO = monHocMapper.monHocDTO(monhoc.get());
            return monHocDTO;
        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi " + MaMonhoc);
        }
    }
    public List<MonHocDTO> search(String search){
        List<Monhoc> monhocList = monHocRepository.search(search);
        List<MonHocDTO> monHocDTOList = new ArrayList<>();
        for (Monhoc monhoc : monhocList) {
            MonHocDTO monHocDTO = monHocMapper.monHocDTO(monhoc);
            monHocDTOList.add(monHocDTO);
        }
        return monHocDTOList;
    }
    public MonHocDTO post(MonHocDTO monHocDTO){
        Optional<Monhoc> monhocPost = monHocRepository.findById(monHocDTO.getMaMonHoc());
        if(monhocPost.isPresent()){
            throw new RuntimeException("Đã tồn tại bản ghi " );
        }
        else{
            Monhoc monhoc = monHocMapper.monhoc(monHocDTO);
            monHocRepository.save(monhoc);
            return monHocDTO;
        }
    }

    public MonHocDTO put(MonHocDTO monHocDTO,String mamonhoc){
        Optional<Monhoc> monhocPut = monHocRepository.findById(mamonhoc);
        if (monhocPut.isPresent()){
            Monhoc monhoc = monHocMapper.monhoc(monHocDTO);
            monhocPut.get().setTenMonHoc(monhoc.getTenMonHoc());
            monHocRepository.save(monhocPut.get());
            return monHocDTO;
        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi " + monHocDTO.getMaMonHoc());
        }
    }

    public Integer delete(String MaMonhoc){
        Optional<Monhoc> monhocDelete = monHocRepository.findById(MaMonhoc);
        List<Baithi> baithis = monhocDelete.get().getBaithis();
        if (monhocDelete.isPresent() && baithis.size() == 0) {
            monHocRepository.deleteById(MaMonhoc);
            return baithis.size();
        }
        else {
            throw new RuntimeException("Không hợp lệ" );
        }
    }
}
