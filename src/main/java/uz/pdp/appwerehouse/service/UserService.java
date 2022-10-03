package uz.pdp.appwerehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.entity.User;
import uz.pdp.appwerehouse.entity.Warehouse;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.payload.UserDto;
import uz.pdp.appwerehouse.repository.UserRepository;
import uz.pdp.appwerehouse.repository.WarehouseRepository;

import java.util.*;

@Service
public class UserService {

    final
    UserRepository userRepository;
    final
    WarehouseRepository warehouseRepository;



    public UserService(UserRepository userRepository, WarehouseRepository warehouseRepository) {
        this.userRepository = userRepository;
        this.warehouseRepository = warehouseRepository;
    }

    // CREAT
    public Result addUser(UserDto userDto){
        boolean exists = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (exists){
            return new Result("Bunday telefon raqam mavjud", false);
        }
        List<Warehouse> warehouseList = warehouseRepository.findAllByIdIn(userDto.getWarehouseId());
        Set<Warehouse> warehouseSet = new HashSet<>(warehouseList);
        User user1 = new User();
        user1.setFirstName(userDto.getFirstName());
        user1.setLastName(userDto.getLastName());
        user1.setPhoneNumber(userDto.getPhoneNumber());
        user1.setPassword(userDto.getPassword());
        user1.setCode("0");
        user1.setWarehouses(warehouseSet);
        User saved = userRepository.save(user1);
        saved.setCode(String.valueOf(saved.getId()));
        userRepository.save(saved);
        return new Result("Ma'lumot muvaffaqiyatli saqlandi", true);
    }

    // GET ALL
    public List<User> allUser(){
        return userRepository.findAll();
    }

    // GET BY ID
    public User getUserById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    // DELETE
    public Result deleteUserById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new Result("Bunday foydalanuvchi mavjud emas", false);
        userRepository.deleteById(id);
        return new Result("Ma'lumot o'chirildi", true);
    }

    // UPDATE
    public Result editUserById(Integer id, UserDto userDto){
        boolean exists = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (exists){
            return new Result("Bunday telefon raqam mavjud", false);
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new Result("Bunday foydalanuvchi mavjud emas", false);

        List<Warehouse> warehouseList = warehouseRepository.findAllByIdIn(userDto.getWarehouseId());
        Set<Warehouse> warehouseSet = new HashSet<>(warehouseList);
        User user1 = optionalUser.get();
        user1.setFirstName(userDto.getFirstName());
        user1.setLastName(userDto.getLastName());
        user1.setPhoneNumber(userDto.getPhoneNumber());
        user1.setPassword(userDto.getPassword());
        user1.setWarehouses(warehouseSet);
        userRepository.save(user1);
        return new Result("Ma'lumot muvaffaqiyatli saqlandi", true);
    }
}
