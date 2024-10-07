package com.rtarcisio.usernotification.services;

import com.rtarcisio.usernotification.domains.Foto;
import com.rtarcisio.usernotification.domains.Person;
import com.rtarcisio.usernotification.dtos.PersonDto;
import com.rtarcisio.usernotification.dtos.input.UsuarioInput;
import com.rtarcisio.usernotification.mappers.ImageMapper;
import com.rtarcisio.usernotification.mappers.PersonMapper;
import com.rtarcisio.usernotification.repositories.FotoRepository;
import com.rtarcisio.usernotification.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class PersonService {

    private ImageMapper imgMapper;
    private PersonMapper personMapper;
    private PersonRepository personRepository;
    private FotoRepository fotoRepository;

    @Autowired
    public PersonService(ImageMapper imgMapper, PersonMapper personMapper, PersonRepository personRepository, FotoRepository fotoRepository) {
        this.imgMapper = imgMapper;
        this.personMapper = personMapper;
        this.personRepository = personRepository;
        this.fotoRepository = fotoRepository;
    }

    @Transactional
    public Person savePerson(UsuarioInput usuarioInput) {

        Person person = new Person();

        if (usuarioInput.getArquivo() != null) {
            try {
                MultipartFile midia = usuarioInput.getArquivo();
                Foto foto = imgMapper.mapToImage(midia);
                person = personMapper.inputToPerson(usuarioInput);
                person = personRepository.saveAndFlush(person);

                foto.setPerson(person);
                foto = fotoRepository.save(foto);
                return personRepository.saveAndFlush(person);


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        person = personMapper.inputToPerson(usuarioInput);
        return personRepository.save(person);


    }


    public PersonDto buscarPorId(Long id) {

        Optional<Person> toFind = personRepository.findById(id);
//        if(!toFind.isPresent())
//            throw new RuntimeException("usuario nao encontrado");

        Optional<Foto> foto = fotoRepository.findByPersonId(id);
        PersonDto dto = personMapper.personToDTO(toFind.get());
        MultipartFile arquivo = null;
        try {
            arquivo = ImageMapper.mapToMultipartFile(foto.get());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        dto.setArquivo(arquivo);

        return null;
    }
}
