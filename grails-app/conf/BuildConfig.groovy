grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

// uncomment (and adjust settings) to fork the JVM to isolate classpaths
//grails.project.fork = [
//   run: [maxMemory:1024, minMemory:64, debug:false, maxPerm:256]
//]

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "WARN" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve true // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()
        mavenRepo "http://maven.springframework.org/release/"
        mavenRepo "http://repo.spring.io/milestone/"
        mavenRepo "https://oss.sonatype.org/content/repositories/snapshots/"
        
        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        runtime 'mysql:mysql-connector-java:5.1.18'
        // runtime 'mysql:mysql-connector-java:5.1.22'
        //compile 'org.springframework.social:spring-social-core:1.0.1.RELEASE'
        //compile 'org.springframework.social:spring-social-facebook:1.0.1.RELEASE'

    
        
        //compile 'org.grails:grails-datastore-gorm:3.1.0.RELEASE'
       // compile 'org.grails:grails-datastore-core:3.1.0.RELEASE'
     //   test 'org.grails:grails-datastore-simple:3.1.0.RELEASE'
   //     compile  'org.grails:grails-datastore-gorm:3.1.0.RELEASE' 
 //         compile ':mongodb-3.0.2.pom'
    }

    plugins {
        
//        runtime ":hibernate:$grailsVersion"
//        runtime ":jquery:1.8.3"
//        runtime ":resources:1.2"
runtime ':hibernate4:4.3.5.2'
    runtime ':jquery:1.11.0.2'
    runtime ":jquery-ui:1.8.24"
    //runtime ":resources:1.2"
//    runtime ':twitter-bootstrap:3.2.0.2'
//    compile ':spring-security-core:2.0-RC4'
//    compile ":rabbitmq:1.0.0"
//        runtime ":facebook-sdk:2.0.0"
//        compile ":spring-security-core:2.0-RC4"        
////        compile ":spring-security-facebook:0.15.4-CORE2"
//        compile":spring-security-twitter:0.6.2"
        
        //        
        //twitter
        //        
        //        
        //
        ////
        //// Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.5"

//        build ":tomcat:$grailsVersion"

//        runtime ":database-migration:1.3.2"

//        compile ':cache:1.0.1'
compile (":events-push:1.0.M7") {
        excludes "resources"
    }
    compile ":rest-client-builder:1.0.3"
    test(":spock:0.7") {
        exclude "spock-grails-support"
    }
    build ':tomcat:7.0.52.1'
    runtime ':database-migration:1.4.0'
    compile ':cache:1.1.3'
    compile ":pretty-time:0.3"
    compile ":tagcloud:0.3"
    compile ':asset-pipeline:1.8.3'
    
//        http://stackoverflow.com/questions/25773385/how-to-upgrade-from-2-2-4-to-2-4-3
    }
}
