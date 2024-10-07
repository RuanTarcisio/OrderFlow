package com.rtarcisio.inventaryms.services;


import org.springframework.stereotype.Service;

@Service
public class PersonService {

//    private ImageMapper imgMapper;
//    private PersonMapper personMapper;
//    private PersonRepository personRepository;
//    private FotoRepository fotoRepository;
//
//    @Autowired
//    public PersonService(ImageMapper imgMapper, PersonMapper personMapper, PersonRepository personRepository, FotoRepository fotoRepository) {
//        this.imgMapper = imgMapper;
//        this.personMapper = personMapper;
//        this.personRepository = personRepository;
//        this.fotoRepository = fotoRepository;
//    }
//
//    @Transactional
//    public Person savePerson(UsuarioInput usuarioInput) {
//
//        Person person = new Person();
//
//        if (usuarioInput.getArquivo() != null) {
//            try {
//                MultipartFile midia = usuarioInput.getArquivo();
//                Foto foto = imgMapper.mapToImage(midia);
//                person = personMapper.inputToPerson(usuarioInput);
//                person = personRepository.saveAndFlush(person);
//
//                foto.setPerson(person);
//                foto = fotoRepository.save(foto);
//                return personRepository.saveAndFlush(person);
//
//
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//        person = personMapper.inputToPerson(usuarioInput);
//        return personRepository.save(person);
//
//
//    }
//
//
//    public PersonDto buscarPorId(Long id) {
//
//        Optional<Person> toFind = personRepository.findById(id);
////        if(!toFind.isPresent())
////            throw new RuntimeException("usuario nao encontrado");
//
//        Optional<Foto> foto = fotoRepository.findByPersonId(id);
//        PersonDto dto = personMapper.personToDTO(toFind.get());
//        MultipartFile arquivo = null;
//        try {
//            arquivo = ImageMapper.mapToMultipartFile(foto.get());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
////        dto.setArquivo(arquivo);
//
//        return null;
//    }
}
