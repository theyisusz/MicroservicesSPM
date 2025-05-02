package co.edu.unicauca.microserviceproject.infra.dto;

import co.edu.unicauca.microserviceproject.entities.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapperCompany {

    @Mapping(source = "company.nit", target = "nitCompany")
    ProjectRequestCompany dto(Project project);

}
