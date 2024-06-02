package com.tscode.LitWorld.Database.UserClass;



import com.tscode.LitWorld.Dto.SignUpDto;
import com.tscode.LitWorld.Dto.UserClassDto;

import java.util.List;

public interface khaibaohamUser {


    // them nguoi dung
    public UserClass adduser(SignUpDto SignUpDto);


    //cap nhat nguoi dung
    public UserClass upClassUser(Integer Id, UserClass UserClass);

    //xoa nguoi dung
    public boolean deletteClassUser(Integer Id);

    // lay danh sach user
    public List<UserClass> getClassUsers();

    // lay nhan vien theo id
    public UserClassDto getoneClassUser(Integer Id);

    // login
    public UserClass findByAccount(String account);
    public UserClass findByEmail(String email);
}



