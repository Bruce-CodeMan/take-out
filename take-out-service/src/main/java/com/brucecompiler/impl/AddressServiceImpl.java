package com.brucecompiler.impl;

import com.brucecompiler.AddressService;
import com.brucecompiler.constant.AddressDefaultStatusConstant;
import com.brucecompiler.context.BaseContext;
import com.brucecompiler.entity.AddressBook;
import com.brucecompiler.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper;

    @Autowired
    public AddressServiceImpl(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public List<AddressBook> list() {
        AddressBook addressBook = new AddressBook();
        addressBook.setUserId(BaseContext.getCurrentId());
        return addressMapper.list(addressBook);
    }

    @Override
    public void save(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(AddressDefaultStatusConstant.NOT_DEFAULT);
        addressMapper.insert(addressBook);
    }

    @Override
    public AddressBook findById(Long id) {
        return addressMapper.findById(id);
    }

    @Override
    public void update(AddressBook addressBook) {
        addressMapper.update(addressBook);
    }

    @Override
    @Transactional
    public void setDefault(AddressBook addressBook) {
        // 1. 将当前用户的所有地址修改为非默认地址
        addressBook.setIsDefault(AddressDefaultStatusConstant.NOT_DEFAULT);
        addressBook.setUserId(BaseContext.getCurrentId());
        addressMapper.updateIsDefaultByUserId(addressBook);

        // 2. 将当前地址改为默认地址
        addressBook.setIsDefault(AddressDefaultStatusConstant.IS_DEFAULT);
        addressMapper.update(addressBook);
    }
}
