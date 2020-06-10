package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class PalTrackerApplication {

   // MysqlDataSource dataSource = new MysqlDataSource();



    public static void main(String[] args){
        SpringApplication.run(PalTrackerApplication.class, args);
    }
//    @Bean
//    public TimeEntryRepository getTimeEntryRepository(){
//        return new InMemoryTimeEntryRepository();
//    }

      @Bean
       public TimeEntryRepository getTimeEntryRepository(DataSource dataSource){

          return new JdbcTimeEntryRepository(dataSource);
       }


    }
