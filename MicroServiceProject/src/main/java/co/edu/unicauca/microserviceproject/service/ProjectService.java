package co.edu.unicauca.microserviceproject.service;
import co.edu.unicauca.microserviceproject.infra.Prototype.ProjectPrototypeRegister;
import co.edu.unicauca.microserviceproject.infra.config.RabbitMQConfig;
import co.edu.unicauca.microserviceproject.infra.config.RestTemplateConfig;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectMapperCompany;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectRequest;
import co.edu.unicauca.microserviceproject.infra.dto.ProjectRequestCompany;
import co.edu.unicauca.microserviceproject.entities.Company;
import co.edu.unicauca.microserviceproject.entities.Project;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import co.edu.unicauca.microserviceproject.repository.CompanyRepository;
import co.edu.unicauca.microserviceproject.repository.CoordinatorRepository;
import co.edu.unicauca.microserviceproject.repository.PostulationRepository;
import co.edu.unicauca.microserviceproject.repository.ProjectRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CoordinatorRepository coordinatorRepository;
    @Autowired
    PostulationRepository postulationRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ProjectMapperCompany projectMapperCompany;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private ProjectPrototypeRegister prototypeRegistry;
    @Autowired
    private SenderService senderService;


    public List<Project> findAll() throws Exception {
        try {
            return projectRepository.findAll();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Project> findAllCompany(Long nit) throws Exception {
        try {
            return projectRepository.findAllByCompany_Nit(nit);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



    public Project findById(Long id) throws Exception {
        return projectRepository.findById(id).get();
    }


    public Project createProject(ProjectRequest dto) throws Exception {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO del proyecto no puede ser nulo");
        }

        Project project = new Project();
        project.setNombre(dto.getNombre());
        project.setResumen(dto.getResumen());
        project.setDescripcion(dto.getDescripcion());
        project.setObjetivo(dto.getObjetivo());
        project.setTiempoMaximo(dto.getTiempoMaximo());
        project.setPresupuesto(dto.getPresupuesto());
        project.setFechaEntregadaEsperada(dto.getFechaEntregadaEsperada());

        Optional<Company> company = companyRepository.findById(dto.getNitCompany());
        if (company.isEmpty()) {
            throw new IllegalArgumentException("La compañía con NIT " + dto.getNitCompany() + " no existe.");
        }

        project.setCompany(company.get());
        Project savedProject = projectRepository.save(project);

        ProjectRequestCompany projectRequestCompany = projectMapperCompany.dto(savedProject);

        // Preparar llamada REST
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProjectRequestCompany> request = new HttpEntity<>(projectRequestCompany, headers);

        ResponseEntity<ProjectRequestCompany> response = restTemplate.postForEntity(
                "http://localhost:8081/CompanyMicroservice/Companies/saveProject",
                request,
                ProjectRequestCompany.class
        );

        ProjectRequestCompany responseBody = response.getBody();
        System.out.println("Respuesta del microservicio: " + responseBody);

        return savedProject;
    }

    public void deleteById(Long id) throws Exception {
        projectRepository.deleteById(id);
    }


}
