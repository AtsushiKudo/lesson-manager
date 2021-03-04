package validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.LessonManagement.repository.EmployeeRepository;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    private final EmployeeRepository EmployeeRepository;

    public UniqueLoginValidator() {
        this.EmployeeRepository = null;
    }

    @Autowired
    public UniqueLoginValidator(EmployeeRepository EmployeeRepository) {
        this.EmployeeRepository = EmployeeRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return EmployeeRepository == null || EmployeeRepository.findByEmployeeCode(value) == null;
    }
}
