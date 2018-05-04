package springboot.springboot;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/employees/")

public class employeeController {

    static Map<Integer, employee> people = Collections.synchronizedMap(new HashMap<Integer, employee>());

    @PostMapping(value = "add")
    public String addNewPerson(employee addone){

        if (addone.getAge() > 0 && addone.getName() != null){
            people.put(addone.getId(), addone);
            return "Add" + addone.getName() + "succsee";
        }
        return addone.getName() + "fail";
    }

    @DeleteMapping(value = "delete/{id}")
    public String deletePerson(@PathVariable Integer id){

        if (id < 0)
            return "id should be more than 0";

        people.remove(id);
        return "delete success!";
    }

    @GetMapping(value = "showlist")
    public List<employee> showAllPerson(){

        List<employee> result = new ArrayList<employee>(people.values());
        return result;
    }
}
