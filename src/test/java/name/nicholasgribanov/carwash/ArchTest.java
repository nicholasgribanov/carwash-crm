package name.nicholasgribanov.carwash;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("name.nicholasgribanov.carwash");

        noClasses()
            .that()
                .resideInAnyPackage("name.nicholasgribanov.carwash.service..")
            .or()
                .resideInAnyPackage("name.nicholasgribanov.carwash.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..name.nicholasgribanov.carwash.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
