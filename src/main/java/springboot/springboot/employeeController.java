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
            return "Add succsee";
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
    //@PostMapping(value = "delete")
    //public String deletePerson(Integer id){
//
    //    if (id < 0)
    //        return "id should be more than 0";
//
    //    people.remove(id);
    //    return "delete success!";
    //}

    @PostMapping(value = "findperson")
    public employee findPerson(Integer id){

        employee result = people.get(id);
        return result;
    }

    @PostMapping(value = "changeperson")
    public String changePerson(employee otherperson){

        if (!people.containsKey(otherperson.getId())){
            return "Have no person";
        }

        people.put(otherperson.getId(), otherperson);
        return "change message success!";
    }

    @GetMapping(value = "showlist")
    public List<employee> showAllPerson(){

        List<employee> result = new ArrayList<employee>(people.values());
        return result;
    }
}
