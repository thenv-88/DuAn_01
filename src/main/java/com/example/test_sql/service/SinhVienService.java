package com.example.test_sql.service;

import com.example.test_sql.dto.SinhVienDTO;
import com.example.test_sql.mapper.SinhVienMapper;
import com.example.test_sql.model.Baithi;
import com.example.test_sql.model.Lop;
import com.example.test_sql.model.Sinhvien;
import com.example.test_sql.repository.CustomeRepository;
import com.example.test_sql.repository.LopRepository;
import com.example.test_sql.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class SinhVienService {
    @Autowired
    LopRepository lopRepository;

    @Autowired
    SinhVienRepository sinhVienRepository;

    @Autowired
    SinhVienMapper sinhVienMapper;

    @Autowired
    CustomeRepository customeRepository;

    @Autowired
    ServletContext application;

    public List<SinhVienDTO> getList(Pageable pageable){
        List<Sinhvien> sinhviens = sinhVienRepository.findAll(pageable).toList();
        List<SinhVienDTO> sinhVienDTOS = new ArrayList<>();
        for (Sinhvien sv: sinhviens) {
            SinhVienDTO sinhVienDTO = sinhVienMapper.toSVDTO(sv);
            sinhVienDTOS.add(sinhVienDTO);
        }
        return sinhVienDTOS;
    }

    public List<SinhVienDTO> List(){
        List<Sinhvien> sinhviens = sinhVienRepository.findAll();
        List<SinhVienDTO> sinhVienDTOS = new ArrayList<>();
        for (Sinhvien sv: sinhviens) {
            SinhVienDTO sinhVienDTO = sinhVienMapper.toSVDTO(sv);
            sinhVienDTOS.add(sinhVienDTO);
        }
        return sinhVienDTOS;
    }

    public List<SinhVienDTO> search(String search){
        List<Sinhvien> sinhviens = sinhVienRepository.search(search);
        List<SinhVienDTO> sinhVienDTOS = new ArrayList<>();
        for (Sinhvien sv: sinhviens) {
            SinhVienDTO sinhVienDTO = sinhVienMapper.toSVDTO(sv);
            sinhVienDTOS.add(sinhVienDTO);
        }
        return sinhVienDTOS;
    }

    public SinhVienDTO getone(String masinhvien){
        Optional<Sinhvien> sinhvien = sinhVienRepository.findById(masinhvien);
        SinhVienDTO sinhVienDTO = null;
        if (sinhvien.isPresent()){
            sinhVienDTO = sinhVienMapper.toSVDTO(sinhvien.get());
        }
        else {
            throw new RuntimeException("Không tồn tại bản ghi");
        }
        return sinhVienDTO;
    }

    public SinhVienDTO post(SinhVienDTO sinhVienDTO){
        Optional<Sinhvien> sinhvien = sinhVienRepository.findById(sinhVienDTO.getMaSinhVien());
        if(sinhvien.isPresent()){
            throw new RuntimeException("Đã tồn tại bản ghi");
        }
        else {
            Optional<Lop> lop = lopRepository.findById(sinhVienDTO.getMaLop());
            Sinhvien sinhvienpost = sinhVienMapper.toSV(sinhVienDTO);
            sinhvienpost.setMaLop(lop.get());
            String filename = sinhVienDTO.getMultipartFile().getOriginalFilename();
            try{
                FileCopyUtils.copy(sinhVienDTO.getMultipartFile().getBytes(),new File("D:\\Quanlysinhvien\\src\\main\\resources\\static\\image\\" +filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
            sinhvienpost.setImage(filename);
            sinhVienRepository.save(sinhvienpost);
            return  sinhVienDTO;
        }
    }

    public SinhVienDTO put(SinhVienDTO sinhVienDTO,String masinhvien){
        Optional<Lop> lop = lopRepository.findById(sinhVienDTO.getMaLop());
        Optional<Sinhvien> sinhvienput = sinhVienRepository.findById(masinhvien);
        sinhvienput.get().setTenSV(sinhVienDTO.getTenSV());
        sinhvienput.get().setDate(sinhVienDTO.getDate());
        sinhvienput.get().setDiaChi(sinhVienDTO.getDiaChi());
        String filename = sinhVienDTO.getMultipartFile().getOriginalFilename();
        try{
            FileCopyUtils.copy(sinhVienDTO.getMultipartFile().getBytes(),new File("D:\\Quanlysinhvien\\src\\main\\resources\\static\\image\\" +filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sinhvienput.get().setImage(filename);
        sinhvienput.get().setMaLop(lop.get());
        sinhVienRepository.save(sinhvienput.get());
        return sinhVienDTO;
    }

    public void delete(String masinhvien){
        Optional<Sinhvien> sinhvien = sinhVienRepository.findById(masinhvien);
        List<Baithi> baithis = sinhvien.get().getBaithis();
        String filename = sinhvien.get().getImage();
        File file = new File("D:\\Quanlysinhvien\\src\\main\\resources\\static\\image\\" +filename);
        if ( baithis.size()<=0){
            file.delete();
            sinhVienRepository.delete(sinhvien.get());
        }
        else {
            throw new RuntimeException("Không hợp lệ");
        }
    }




}
