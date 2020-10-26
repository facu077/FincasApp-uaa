package ar.edu.um.fincaspp.uaa;

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
            .importPackages("ar.edu.um.fincaspp.uaa");

        noClasses()
            .that()
                .resideInAnyPackage("ar.edu.um.fincaspp.uaa.service..")
            .or()
                .resideInAnyPackage("ar.edu.um.fincaspp.uaa.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..ar.edu.um.fincaspp.uaa.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
