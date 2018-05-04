package springboot.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/employees/")

public class employeeController {

    static Map<Integer, employee> people = Collections.synchronizedMap(new HashMap<Integer, employee>());

    @PostMapping(value = "add")
    public String addNewPerson(employee addone){

        if (addone.getAge() > 0 && addone.getName() != null){
            people.put(addone.getId(), addone);
            return addone.getName() + "succsee";
        }
        return addone.getName() + "fail";
    }

    @GetMapping(value = "showlist")
    public List<employee> showAllPerson(){

        List<employee> result = new ArrayList<employee>(people.values());
        return result;
    }
}
