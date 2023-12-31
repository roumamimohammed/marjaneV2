package ma.yc.marjane.services.impl;

import jakarta.transaction.Transactional;
import jdk.jshell.execution.Util;
import ma.yc.marjane.dto.UserDto;
import ma.yc.marjane.dto.projectDto.AdminGeneralDto;
import ma.yc.marjane.entity.AdminGeneral;
import ma.yc.marjane.entity.User;
import ma.yc.marjane.mapper.AdminGeneralMapper;
import ma.yc.marjane.repository.AdminGeneralAuthentificationRespository;
import ma.yc.marjane.services.AuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Qualifier("AdminGeneralAuthentificationServiceImpl")
public class AdminGeneralAuthentificationServiceImpl implements AuthentificationService<AdminGeneralDto> {



    @Autowired
    private AdminGeneralAuthentificationRespository adminGeneralAuthentificationRespository;




    @Override
    public AdminGeneralDto login(AdminGeneralDto userDto) {
        AdminGeneral user1 = this.adminGeneralAuthentificationRespository.findByEmail(userDto.getEmail());
        if(user1 != null){
            if((userDto.getPassword()).equals(user1.getPassword())){
                AdminGeneralDto adminGeneralDto = new AdminGeneralDto();
                adminGeneralDto.setEmail(user1.getEmail());
                adminGeneralDto.setPassword(user1.getPassword());
                adminGeneralDto.setNom(user1.getNom());
                return adminGeneralDto;
            }
        }
        return null;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public AdminGeneralDto register(AdminGeneralDto adminGeneralDto) {
        AdminGeneral adminGeneral = AdminGeneralMapper.adminGeneralMapper.toEntity(adminGeneralDto);
        adminGeneral = this.adminGeneralAuthentificationRespository.save(adminGeneral);
        return AdminGeneralMapper.adminGeneralMapper.toDto(adminGeneral);
    }
}
