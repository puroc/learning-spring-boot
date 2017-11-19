package com.example.springboot;

import com.example.springboot.domain.Guest;
import com.example.springboot.domain.Login;
import com.example.springboot.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by puroc on 2017/11/19.
 */
@Component
public class Test implements CommandLineRunner {

    @Autowired
    private GuestService guestService;

//    @Test
//    public void save(){
//      User user = new User();
//      user.setId(UUID.randomUUID().toString());
//      user.setName("ada");
//      user.setSignupDate(new Date());
//      String id = userService.save(user);
//      LOGGER.info(JSON.toJSON(id));
//    }
//
//    @Test
//    public void findAll(){
//      List<User> users = userService.findAll();
//      for(User u:users){
//              LOGGER.info(u.getId()+"\t"+u.getName()+"\t"+u.getSignupDate());
//      }
//    }

    public void saveByTenantId(String tenantId) {
        Login.setTenantId(tenantId);
        Guest guest = new Guest();
        guest.setId(1);
        guest.setName("张三");
        guest.setTelephone("56785678");
        guest.setAddress("上海市张扬路88号");
        guest.setEmail("zhangsan@gmail.com");
        int id = guestService.save(guest);
        System.out.println(id + " has been insert");
    }

    @Override
    public void run(String... strings) throws Exception {
//        saveByTenantId("hotel_1");
//        saveByTenantId("hotel_2");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Global.getInstance().getTenants().set("hotel_1");
                for (Guest guest : guestService.findAll()) {
                    System.out.println(guest.getName());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Global.getInstance().getTenants().set("hotel_2");
                for (Guest guest : guestService.findAll()) {
                    System.out.println(guest.getName());
                }
            }
        }).start();
    }
}
