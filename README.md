Spring And Spring Web - MVC
-------------------------------------------------------------------------------------

    Objectives
    ----------------------------------
    Spring Core Basics, IOC Container and Dependency Injection  
    Spring Auto wiring,
    Spring MVC and Model Class, Handler Mappings and Exception Resolvers, Coding Data Validations, Coding Views, 
    Handler Mappings and Exception Resolvers & Spring MVC application

    Lab Setup
    --------------------------------------

        JDK 1.8
        STS latest (include Maven)
        MySQL Community Server 8 or above

    Spring Intro
    -------------------------

        1. Spring is a development platform and a group of frameworks as well that support a variety of Enterprise Level Apps.

        2. Highly Modular and is hence very light weight.

            Spring Core                         is that basic module needed for any spring based application (for IoC/DI)
            Spring Context                      offers ApplicationContext as a container.
            Spring Expression Language          offers External congfiguarations.
            Spring Boot                         offers Rapid Application Development (through auto-configuaration)
            Spring Jdbc                         offers JDBC based Repository support
            Spring Data JPA                     offers dynamic Repositories using (JPA and hibernate)
            Spring Web                          offers support for Web-MVC applications and REST-apis
            Spring Security                     offers Authorization and Authentication
            Spring Test                         offers a testing platform for all spring based applications
            Spring AOP                          offers Aspect Oriented Programming
            Spring Batch                        offers Batch - Job Processing
            ......etc., (18+ such modules)

        3. Interoparable

    Spring Core, Spring Context and SpEL
    -------------------------------------------------

        Spring Core offers Dependency Injection through a InversionOfControl (IoC) implementation.

        Dependency?
            if a software component need to invoke a function of a different component to accomplish
            a teask, than the former is said to depend on the later functionally.

            example, A service depends on a repository/DAO functionally.

            in this case, the service is called site and the DAO is called dependency.

            interface  EmployeeRepo {

            }

            class EmployeeRepoJdbcImpl implements EmployeeRepo{

            } 
            
            class EmployeeRepoJpaImpl implements EmployeeRepo{

            }

            interface EmployeeService{

            }

            class EmployeeServiceImpl impleemnts EmployeeService {

                private EmployeeRepo empRepo;

                public EmployeeServiceImpl(){
                    //this.empRepo = new EmployeeRepoJdbcImpl();
                    this.empRepo = new EmployeeRepoJpaImpl();
                }
            }

        Dependency Injection?

            interface EmployeeService{

            }

            class EmployeeServiceImpl impleemnts EmployeeService {

                private EmployeeRepo empRepo;

                public EmployeeServiceImpl(){

                }
                
                public EmployeeServiceImpl(EmployeeRepo empRepo){
                    this.empRepo = empRepo;
                }

                public void setEmpRepo(EmployeeRepo empRepo){
                    this.empRepo = empRepo;
                }
            }

        Container       is a class that manages the life cycle of a object. The container can 
                        create, inject dependencies, supply when needed and destroy an object.

                        Containers offered by Spring are
                            BeanFactory (interface)         Spring Core
                            ApplicationContext (interface)  Spring Context

        Bean            is  the object whose life cyle is managed by container.

        Component       is the class of the bean.

        Bean Confguaration

            is a process used to inform the Contaienr a list of Components to which 
            the contaienr has to create and manage Beans.

            Xml Based Bean Conf
            
            Annotation Based Bean Conf  & Java Based Bean Conf

                @Configuaration
                @ComponentScan("base.package")
                public class MyBeanConf {

                    @Bean
                    public Scanner kbin(){
                        return new Scanner(System.in);
                    }
                }

                @Component
                    |- @Repository
                    |- @Service
                    |- @Controller
                    |- @RestController
                    |- @Aspect
                    |- @Advice
                    ......etc.,

                Every bean has a bean-name or bean-id.
                    For @Component and its dirived annotations, the camel case of the Class Name will be the bean Id, or
                    otherwise the bean-id can be supplied in the annotation
                    
                    @Component                          //homeScreenView is the bean id,    context.getBean("homeScreenView")
                    public class HomeScreenView{

                    }
                    
                    @Component("aesv")                   //aesv is the bean id
                    public class AddEmployeeScreenView{

                    }

                    For @Bean, the method name is the bean id. "kbin" is the bean id of the Scanner Object.

                @Scope("singleton|prototype|reqeust|sesson|global-session")

                @Autowired              byType
            
                @Autowired              byName
                @Qualifier("beanName")

                                        @Autowired annotation can be used as

                                            Field Injection
                                                @Autowired
                                                private Scanner scan;

                                            Constructor Injection
                                                @Autowired
                                                public HoemScreenView(Scanner scan){
                                                    this.scan=scan;
                                                }

                                            Setter Injection
                                                @Autowired
                                                public void setScan(Scanner scan){
                                                    this.scan=scan;
                                                }

                @Value("spring-expression") is used to externalized configuaration.
                
    Spring Core and Spring Context Dependency
    
        <dependencies>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-core</artifactId>
                <version>5.3.27</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-context</artifactId>
                <version>5.3.27</version>
            </dependency>
        </dependencies>

    Spring Boot
    -------------------------

        is a spring module that offers auto-configuaration and provides
        rapid application development.

        Module                  Configuaration
        -------------------------------------------------------------------------------------
        Spring Web              DispatcherServlet, InternalResourceViewResolver , ..etc.,
        Spring Data             DataSource Pool,Transaction Management ..etc.,
        Spring Security         Authentication Macahnisim, Users and Roels ..etc.,


        Auto-Config will make these minimum boiler plate configs after
        detecting the module in the dependencies. As a result, the
        developer need not do the same but still can customize if needed.
        This helps in RAD.

        Spring Boot uses specail artifacts called '-starter-' packages, that
        not only provide the needed jars but also contian the default configs.

        Spring Boot also supports embed servers. That means while
        developing any enterprise level application web-servers and
        application-servers are embeded into the spring boot application
        making it a standalone executable. This also makes it container ready.

        @SpringBootApplication
        public class SpringBootIocDemoApplication {

            public static void main(String[] args) {
                SpringApplication.run(SpringBootIocDemoApplication.class, args);
            }

        }

        @SpringBootApplication  =   @Configuaration, @PropertySource, @ComponentScan

        SpringApplication.run(SpringBootIocDemoApplication.class, args);

            1. Create a ApplicationContext.
            2. Load all components and beans into the context.
            3. Execute all Runner Classes (if any).
            4. Start the embeded server (if any).
            5. Once the server shuts down or if no server started, the application context is destroyed
            6. The application terminates.

        A Runner class is any sub-class of CaommandLineRunner interface, that 
        overrides 'public void run(args)' method.

   Standard Java Dynamic Web Application Project Structure
    ----------------------------------------------------------------------

        my-web-project
            | - src
            | - webapp
                 |- WEB-INF
                 |      |- lib              //.jar                              managed by maven
                 |      |- classes          //.class                            managed by maven (javac)
                 |      |- viewsOrPages     //.html,.css,.js,.jsp ...etc.,      managed by developer
                 |- META-INF
                 |- web.xml                 // web deployment descriptor
                                            // application title, number of servlet and their url mappings
                                            // application or servlet level parameters like database configs, ..etc.,
                                            // filters, tag libraries ..etc.,
                                            // a list of home pages in order of presedency.

                                            //is now replaced by a class called WebServletAdapter and
                                            //all the above configs are done via annotations.
                                            
    Spring Web

        Dynamic Web Applications

            Are applictions that are capable of generating html content dynamically
            on the server and pas that content as a response to the client.

        Dynamic Web Application construction in JEE

            was using servlets and JSPs. 
            
            Servlets were used to recevie the http-request,
            parse the request and extract the data from the request and execute relevent
            operations and the final result of the operations were shared with JSP-pages.

            JSP-pages used to recevie the data (model) from Servlets and embed the
            data ina html-content and the final html-content rendered /generated
            is sent as a response.

        Model-View-Controller Archetecture

            Repositories <--entites--> Services <--model--> Controllers <--- Request------ Client
                                                            |                               ↑
                                                            | (model)                       |
                                                            |                               |
                                                            ↓                               |
                                                            Views   -----Response---------->|

            Controllers     wee servlets whsoe job is to parse the request and extract data from request
                            execute the relevent services and pass the result as model to the View

            Views           was JSP-Page / JFC / XHTML / ThymeLeaf ...etc.,
    

        Model-View-Controller FrontController Archetecture followed in Spring Web
        
            Repositories <--entites--> Services <--model--> Controllers <--model-- FrontController <--- Request------ Client
                                                                ↓                     ↑   |                               ↑
                                                                |----modelAndView-----|   | (model)                       |
                                                                                          |                               |
                                                                                          ↓                               |
                                                                                          Views   -----Response---------->|

            FrontController is provided by Spring Web. DispatcherServlet from Spring Web acts as FrontController.

                            This DispatcherServlet as a FrontController is resposnible to
                                1. Recevie all requests from Client
                                2. Extract any data or parameters from the request
                                3. Identify the respective action in a Controller for the incoming request
                                4. Invoke the identified action from the Controller and pass the extracted data ss model
                                5. Recevie the resultant model (if any) and ViewName from the action.
                                6. Share the resultant model with the view (that is identified by the viewName).

            Views           was JSP-Page / JFC / XHTML / ThymeLeaf ...etc.,

            Controllers     are any POJO class marked with @Controller annotation.

                            These Controller Classes are expected to have methods called actions.
                            These action methods must have relevent arguments to receive data (model) from FrontController.
                            These action methods must return a viewName as a String or viewName and model as an object of ModelAndView.

            How would a FrontController identify a relevent action from a Controller for an incoming Request?

                UrlHanlerMapping
                    |
                    |- SimpleUrlHandlerMapping

                SimpleUrlHandlerMapping

                        it expects each action to be marked with @RequestMapping(url,httpMethod).

                        Based on the url and method of the incoming request, the relelvent action is identified.

                        For Example, assuming the server is running on localhost:8080

                        @Controller
                        public class HomeController{
                            
                            @RequestMapping("/")                    /* http://localhost:8080 any method like GET or POST*/
                            public String indexAction(){
                                return "index";
                            }

                            @RequestMapping(value="/about",method=HttpMethod.GET) /* http://localhost:8080/about only for GET*/
                            public String aboutUsAction(){
                                return "aboutUs";
                            }
                        }

            How would a FrontController identify a VIEW for a given viewName?

                ViewResolver
                    |
                    |- BeanResourceViewResolver         (we have to give a .properties file mapping viewNAme=ViewPath)
                    |- XmlResourceViewResolver          (we have to give a .xml file mapping viewNAme=ViewPath)
                    |- InternalResourceViewResolver     (default for SpringBoot)

                InternalResourceViewResolver

                    ViewPath = prefix + viewName + suffix (where prefix and suffix are configurable properties of I-R-V-R)

                    assuming prefix is '/WEB-INF/pages' and suffix is '.jsp' 
                    for a given viewName 'index'
                    View is resolved as '/WEB-INF/pages/index.jsp'

        @ReqeustMapping
            |
            |- @GetMapping
            |- @PostMapping

        @RequestParam           used to retrive one single param from the reqeust.
        @ModelAttribute         used to retrive all data from a form into a Java Object.
                                used to pass data from controller to view.

        Thymeleaf
            
            The Thymeleaf is an open-source Java library that is licensed under the Apache License 2.0. It is a HTML5/XHTML/XML template engine. It provides full integration with Spring Framework.

            Thymeleaf supports 
                variable expressions (${...}) like Spring EL and executes on model attributes
                asterisk expressions (*{...}) execute on the form backing bean (modelAttributes)
                hash expressions (#{...}) are for internationalization
                link expressions (@{...}) rewrite URLs.

            <dependency>  
                <groupId>org.springframework.boot</groupId>  
                <artifactId>spring-boot-starter-thymeleaf</artifactId>  
            </dependency>  

            To activate thymeleaf on .html
                
                <html lang="en" xmlns:th="http://www.thymeleaf.org">  
            
            application.properties

                spring.thymeleaf.cache=false  
                spring.thymeleaf.suffix=.html  
            
            Thymeleaf HTML attributes

                th:text
                th:value
                th:field
                th:object
                th:href
                th:if
                th:class
                th:each="loopingVariable : ${arrayOrListOrSet}"
                th:insert
                th:replace
            
                data-th-text
                data-th-field
                data-th-value
                        ...etc., for html 5

            For a deep reading:
                https://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html

    Spring Data

        is a spring framework moduel that offers dynamic implementation to repositories.

    Spring Data JPA 

        is a sub-module of spring data that deals with repositories based on RDBMs.

        CrudRepository
            |
            |- JpaRepoisitory<E,pkType>

                E save(E);
                Optional<E> findById(pkType id);
                List<E> findAll():
                boolean existsById(pkType id);
                void deletebyId(pkType id);

            @Entity
            @Table(name="contacts")
            public class Contact {

                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                private Integer contactId;
                private String fullName;
                private String mobileNumber;
                private String meilId;
                private LocalDate dateOfBith;
            
                ///......
            }            

            public interface ContactRepo extends JpaRepository<Contact, Integer> {
              
            }   