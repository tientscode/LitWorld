package com.tscode.LitWorld.Controller.CLien;

import com.tscode.LitWorld.Database.UserClass.UserClass;
import com.tscode.LitWorld.Database.UserClass.khaibaohamUser;
import com.tscode.LitWorld.Dto.SignUpDto;
import com.tscode.LitWorld.Dto.UserClassDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class ResponseApi {

    @Autowired
    private khaibaohamUser khaibaohamUser;


    // api them nguoi dung
    @PostMapping("/admin/add")
    public UserClass adduser(@RequestBody SignUpDto signUpDto) {
        return khaibaohamUser.adduser(signUpDto);
    }

    @GetMapping("/admin/list")
    public List<UserClass> getAlClassUserList() {
        return khaibaohamUser.getClassUsers();
    }

    @PutMapping("/admin/update")
    public UserClass updateClassUser(@RequestParam("Id") Integer Id, @RequestBody UserClass UserClass) {
        return khaibaohamUser.upClassUser(Id, UserClass);
    }

    //api xoa nguoi dung
    @DeleteMapping("/admin/detele/{Id}")
    public boolean deleteClassUser(@PathVariable("Id") Integer Id) {

        return khaibaohamUser.deletteClassUser(Id);
    }

    @GetMapping("/admin/list/user/{Id}")
    public UserClassDto getClassUser(@PathVariable("Id") Integer Id) {
        return khaibaohamUser.getoneClassUser(Id);
    }


}
