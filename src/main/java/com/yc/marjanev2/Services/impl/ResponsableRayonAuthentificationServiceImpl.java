package ma.yc.marjane.services.impl;

import jakarta.transaction.Transactional;
import ma.yc.marjane.dto.UserDto;
import ma.yc.marjane.dto.projectDto.ResponsableDto;
import ma.yc.marjane.entity.Responsable;
import ma.yc.marjane.mapper.Mapper;
import ma.yc.marjane.mapper.impl.ResponsableMapperImpl;
import ma.yc.marjane.repository.ResponsableAuthRepository;
import ma.yc.marjane.services.AuthentificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ResponsableRayonAuthentificationService")
@Transactional
public class ResponsableRayonAuthentificationServiceImpl implements AuthentificationService<ResponsableDto> {


    private ResponsableAuthRepository responsableAuthRepository;
    private  Mapper<Responsable,ResponsableDto> responsableMapper ;
//    @Autowired
    public ResponsableRayonAuthentificationServiceImpl(ResponsableAuthRepository responsableAuthRepository) {
        this.responsableAuthRepository = responsableAuthRepository;
    }
    @Override
    public ResponsableDto login(ResponsableDto responsableDto) {
        Responsable Responsable = this.responsableAuthRepository.findByEmail(responsableDto.getEmail());
        if (Responsable !=null){
            // TODO: 3/11/2023 check password after you hash it

                return this.responsableMapper.toDto(Responsable);
            }
            return  null;

        }



    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public ResponsableDto register(ResponsableDto responsableDto) {
        return null;
    }


}
