package com.example.springboot.actviti;

import com.example.springboot.actviti.util.SecurityUtil;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootActvitiApplicationTests {


    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private SecurityUtil securityUtil;

    @Test
    public void contextLoads() {
        securityUtil.logInAs("salaboy");
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        processDefinitionPage.getContent().forEach(System.out::println);
    }

}
