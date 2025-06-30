package com.brucecompiler.controller.user;

import com.brucecompiler.AddressService;
import com.brucecompiler.constant.MessageConstant;
import com.brucecompiler.constant.StatusCodeConstant;
import com.brucecompiler.entity.AddressBook;
import com.brucecompiler.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/addressBook")
public class AddressBookController {

    private final AddressService addressService;

    @Autowired
    public AddressBookController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/list")
    public Result<List<AddressBook>> list() {
        List<AddressBook> addressBookList = addressService.list();
        return Result.success(addressBookList);
    }

    @PostMapping
    public Result<Object> add(@RequestBody AddressBook addressBook) {
        addressService.save(addressBook);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<AddressBook> get(@PathVariable Long id) {
        AddressBook addressBook = addressService.findById(id);
        return Result.success(addressBook);
    }

    @PutMapping
    public Result<Object> update(@RequestBody AddressBook addressBook) {
        addressService.update(addressBook);
        return Result.success();
    }

    @PutMapping("/default")
    public Result<Object> setDefault(@RequestBody AddressBook addressBook) {
        addressService.setDefault(addressBook);
        return Result.success();
    }

    @DeleteMapping("/")
    public Result<Object> deleteById(Long id) {
        addressService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/default")
    public Result<AddressBook> getDefault() {
        List<AddressBook> addressBookList = addressService.getDefault();
        if(addressBookList != null && addressBookList.size() == 1) {
            return Result.success(addressBookList.get(0));
        }
        return Result.error(StatusCodeConstant.FAILURE, MessageConstant.FIND_DEFAULT_ADDRESS_FAILED);
    }
}
