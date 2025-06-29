package com.brucecompiler;

import com.brucecompiler.entity.AddressBook;

import java.util.List;

public interface AddressService {

    List<AddressBook> list();

    void save(AddressBook addressBook);

    AddressBook findById(Long id);
}
