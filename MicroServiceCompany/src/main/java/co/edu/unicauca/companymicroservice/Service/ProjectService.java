package co.edu.unicauca.companymicroservice.Service;


import co.edu.unicauca.companymicroservice.Entities.Company;
import co.edu.unicauca.companymicroservice.Entities.Project;
import co.edu.unicauca.companymicroservice.Infra.DTO.ProjectRequestCompany;
import co.edu.unicauca.companymicroservice.Infra.Mappers.ProjectMapper;
import co.edu.unicauca.companymicroservice.Infra.Mappers.UsuarioMapper;
import co.edu.unicauca.companymicroservice.Repositories.CompanyRepository;
import co.edu.unicauca.companymicroservice.Repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public ProjectRequestCompany createProject(ProjectRequestCompany dto) throws Exception {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO del proyecto no puede ser nulo");
        }

        Optional<Company> company = companyRepository.findById(String.valueOf(dto.getNitCompany()));
        if (company.isEmpty()) {
            throw new IllegalArgumentException("La compañía con NIT " + dto.getNitCompany() + " no existe.");
        }

        Project project = projectMapper.projectToEntity(dto, company.get());

        Project savedProject = projectRepository.save(project);
        company.get().getProyectos().add(savedProject);

        return dto;
    }

    public void deleteById(Long id) throws Exception {
        projectRepository.deleteById(id);
    }


}
