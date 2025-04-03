package co.edu.unicauca.domain.services;

import co.edu.unicauca.domain.entities.Company;
import co.edu.unicauca.infra.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Brayan
 */
public class CompanyService {

    private ICompanyRepository repository;

    public CompanyService(IRepository repository) {
        this.repository = (ICompanyRepository) repository;
    }

    public boolean registreCompany(Company company) {
        if (validarcampos(company.getNombre(), company.getEmail(), company.getNombrecontaccto(), company.getApellido(), company.getCargo(), company.getTelefono(), company.getSector())) {
            return repository.save(company);
        } else {
            return false;
        }
    }

    public Company obtenerCompanyPorUser(String username) {
        return repository.getCompanyWithUser(username);
    }

    public List<Company> listarEmpresas() {
        List<Object> objects = repository.list();
        List<Company> companies = new ArrayList<>();
        for (Object obj : objects) {
            if (obj instanceof Company) {
                companies.add((Company) obj);
            }
        }
        return companies;
    }

    public boolean validarcampos(String nombre, String email, String nombrecontacto, String apellido, String cargo, String telefono, String sector) {
        // Validar que el nombre y apellido no contengan números
        if (contieneNumeros(nombre)) {
            Messages.showMessageDialog("El campo Nombre no puede contener números.", "Atención");
            return false;
        }
        if (contieneNumeros(sector)) {
            Messages.showMessageDialog("El campo Sector no puede contener números.", "Atención");
            return false;
        }
        if (contieneNumeros(cargo)) {
            Messages.showMessageDialog("El campo Cargo no puede contener números.", "Atención");
            return false;
        }
        if (contieneNumeros(nombrecontacto)) {
            Messages.showMessageDialog("El campo Nombre de contacto no puede contener números.", "Atención");
            return false;
        }
        if (contieneNumeros(apellido)) {
            Messages.showMessageDialog("El campo Apellido no puede contener números.", "Atención");
            return false;
        }

        // Validar que el email tenga un formato válido
        if (!validarEmail(email)) {
            Messages.showMessageDialog("El campo Email no tiene un formato válido.", "Atención");
            return false;
        }

        // Validar que el teléfono solo contenga números
        if (telefono == null || !esNumero(telefono.trim())) {
            Messages.showMessageDialog("El campo Teléfono solo puede contener números.", "Atención");
            return false;
        }

        return true;
    }

    public static boolean contieneNumeros(String texto) {
        return texto.matches(".*\\d.*");
    }

    public boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean esNumero(String texto) {
        return texto.matches("\\d+");
    }

}
