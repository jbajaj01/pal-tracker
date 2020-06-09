package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private String s1;
    private String s2;
    private String s3;
    private String s4;




    public EnvController(@Value("${PORT:NOT SET}") String s1, @Value("${MEMORY_LIMIT:NOT SET}") String s2, @Value("${CF_INSTANCE_INDEX:NOT SET}") String s3, @Value("${CF_INSTANCE_ADDR:NOT SET}") String s4){
       this.s1=s1;
       this.s2=s2;
       this.s3=s3;
       this.s4=s4;

    }


    @GetMapping("/env")
    public Map getEnv(){

        Map environment_vars = new HashMap();


        environment_vars.put("PORT", s1);
        environment_vars.put("MEMORY_LIMIT", s2);
        environment_vars.put("CF_INSTANCE_INDEX", s3);
        environment_vars.put("CF_INSTANCE_ADDR", s4);


        return environment_vars;

    }
}
