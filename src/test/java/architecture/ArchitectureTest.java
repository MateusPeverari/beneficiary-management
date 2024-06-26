package architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ArchitectureTest {
  private static JavaClasses classes;

  private final static String ROOT_PACKAGE = "com.health.beneficiary";
  private final static String MODEL_PACKAGE = "domain.model";
  private final static String SERVICE_PACKAGE = "domain.service";
  private final static String ADAPTERS_PACKAGE = "infrastructure.adapters";
  private final static String INFRASTRUCTURE_PACKAGE = "infrastructure";
  private final static String APPLICATION_PACKAGE = "application";
  private final static String EXCEPTION_PACKAGE = "domain.exception";

  @BeforeAll
  public static void setup() {
    classes = new ClassFileImporter().importPackages(ROOT_PACKAGE);
  }

  @Test
  void testDependenciesModel() {
    checkNoDependencyFromTo(MODEL_PACKAGE, SERVICE_PACKAGE, classes);
    checkNoDependencyFromTo(MODEL_PACKAGE, INFRASTRUCTURE_PACKAGE, classes);
    checkNoDependencyFromTo(MODEL_PACKAGE, APPLICATION_PACKAGE, classes);
    checkNoDependencyFromTo(MODEL_PACKAGE, EXCEPTION_PACKAGE, classes);
  }

  @Test
  void testAccessDependenciesService() {
    checkNoAccessFromTo(SERVICE_PACKAGE, ADAPTERS_PACKAGE, classes);
    checkNoDependencyFromTo(SERVICE_PACKAGE, ADAPTERS_PACKAGE, classes);
  }

  @Test
  void testRepositoryNameRules() {
    ArchRule servicePackageRule = classes()
        .that().resideInAPackage("..repository..")
        .should().haveNameMatching(".*Repository");
    servicePackageRule.check(classes);
  }

  @Test
  void testControllerNameRules() {
    ArchRule servicePackageRule = classes()
        .that().resideInAPackage("..rest")
        .should().haveNameMatching(".*Controller");
    servicePackageRule.check(classes);
  }

  @Test
  void testRepositoryTypeRules() {
    ArchRule servicePackageRule = classes()
        .that().resideInAPackage("..repository..")
        .should().beInterfaces();
    servicePackageRule.check(classes);
  }

  @Test
  void testAdapterImpl() {
    ArchRule servicePackageRule = classes()
        .that().resideInAPackage("..persistence")
        .should().implement(JavaClass.Predicates.resideInAPackage("..output.."));
    servicePackageRule.check(classes);
  }

  @Test
  void testControllerImpl() {
    ArchRule servicePackageRule = classes()
        .that().resideInAPackage("..rest")
        .should().implement(JavaClass.Predicates.resideInAPackage("..adapters.."));
    servicePackageRule.check(classes);
  }

  @Test
  void testServiceImpl() {
    ArchRule servicePackageRule = classes()
        .that().resideInAPackage("..service")
        .should().implement(JavaClass.Predicates.resideInAPackage("..input.."));
    servicePackageRule.check(classes);
  }

  @Test
  void testFieldsDomain() {
    ArchRule fieldsRule =
        fields().that().areDeclaredInClassesThat().areNotEnums().and(
                JavaMember.Predicates.declaredIn(JavaClass.Predicates.resideInAnyPackage("..domain..")))
            .should()
            .bePrivate().because(" it is standard");
    fieldsRule.check(classes);
  }

  @Test
  void testFieldsService() {
    ArchRule fieldsRule =
        fields().that().areDeclaredInClassesThat().resideInAPackage("..service..")
            .should()
            .bePrivate().andShould().beFinal().because(" it is standard");
    fieldsRule.check(classes);
  }

  private void checkNoDependencyFromTo(
      String fromPackage, String toPackage, JavaClasses classesToCheck) {
    noClasses()
        .that()
        .resideInAPackage(fullyQualified(fromPackage))
        .should()
        .dependOnClassesThat()
        .resideInAPackage(fullyQualified(toPackage))
        .check(classesToCheck);
  }

  private void checkNoAccessFromTo(
      String fromPackage, String toPackage, JavaClasses classesToCheck) {
    noClasses()
        .that()
        .resideInAPackage(fullyQualified(fromPackage))
        .should()
        .accessClassesThat()
        .resideInAPackage(fullyQualified(toPackage))
        .check(classesToCheck);
  }

  private String fullyQualified(String packageName) {
    return ROOT_PACKAGE + '.' + packageName;
  }
}
